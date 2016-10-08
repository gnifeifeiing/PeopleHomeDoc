package com.zkr.peoplehomedoc.utils;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.zkr.peoplehomedoc.ui.login.LoginActivity;

import java.util.Map;



/**
 * @PackageName: ---------com.zkr.jsw.utils
 * @Description: ---------格式化
 * @author: ---------------LF
 * @date: -----------------2016/7/26 9:53
 * @Copyright: -----------中科软
 */
public class ParseJsonUtils {
    /**
     * @Description: -------格式化服务器返回的头部数据
     * @author --------------LF
     * @date ----------------2016/7/26 10:21
     */
    public static String getParseData(Activity context,String json){
        try {
            Map<String, Object> obj = JsonUtils.getMapObj(json);
            if (obj.get("success").toString().equals("0")) {
                Toast.makeText(context, obj.get("msg").toString(),Toast.LENGTH_SHORT).show();
            } else if (obj.get("success").toString().equals("1")) {
                return obj.get("data").toString();
            } else if (obj.get("success").toString().equals("403")) {
                Toast.makeText(context, "无权访问",Toast.LENGTH_SHORT).show();
            } else {//401
                Toast.makeText(context, "登录过期",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                context.finish();
            }
        } catch (Exception e) {
        }
        return "";
    }
}
