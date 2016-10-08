package com.zkr.peoplehomedoc.widget;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.zkr.peoplehomedoc.utils.Constants;
import com.zkr.peoplehomedoc.utils.OptsharepreInterface;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * @Description: -------StringRequest请求封装
 * @author --------------LF
 * @date ----------------2016/8/26 14:34
 */
public class IStringRequest extends StringRequest {

    private Context mContext;
    private OptsharepreInterface sharePre;
    private static Map<String, String> headers = new HashMap<String, String>();

    public IStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, Constants.SERVER_ADDRESS +url, listener, errorListener);
        setOutTime();
    }

    public IStringRequest(final Context context, int method, String url, Response.Listener<String> listener) {
        super(method,Constants.SERVER_ADDRESS + url, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"服务器出问题了",Toast.LENGTH_SHORT).show();
            }
        });
        this.mContext=context;
        setOutTime();
    }

    /**
     * 设置访问自己服务器时必须传递的参数，密钥等
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        sharePre=new OptsharepreInterface(mContext);
        headers.put("access-token",sharePre.getPres("tokenApi") );//添加头部token令牌信息
        return headers;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try{
            String type=response.headers.get("content-type");
            if(type==null){
                type="charset=UTF-8";
                response.headers.put("content-type",type);
            }else if (!type.contains("UTF-8")){
                type+=";"+"charset=UTF-8";
                response.headers.put("content-type",type);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    private void setOutTime(){
        //设置超时时间
        setRetryPolicy(new DefaultRetryPolicy(
                Constants.VOLLEY_TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
}
