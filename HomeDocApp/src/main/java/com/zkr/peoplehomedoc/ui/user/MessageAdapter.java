package com.zkr.peoplehomedoc.ui.user;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zkr.peoplehomedoc.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * // 		         _ooOoo_
 // 		        o8888888o
 // 	  	        88" . "88
 //                 (| -_- |)
 //                  O\ = /O
 //              ____/`---*\____
 //               . * \\| |// `.
 //             / \\||| : |||// \
 //           / _||||| -:- |||||- \
 //             | | \\\ - /// | |
 //            | \_| **\---/** | |
 //           \  .-\__ `-` ___/-. /
 //            ___`. .* /--.--\ `. . __
 //        ."" *< `.___\_<|>_/___.* >*"".
 //      | | : `- \`.;`\ _ /`;.`/ - ` : | |
 //         \ \ `-. \_ __\ /__ _/ .-` / /
 //======`-.____`-.___\_____/___.-`____.-*======
 // `=---=*
 //
 // .............................................

 * Created by lenovo on 2016/3/25.
 */

public class MessageAdapter extends BaseAdapter {

    List<Map<String,Object>> list = new ArrayList<>();
    Context context;

    public MessageAdapter(List<Map<String,Object>> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view==null){
            view= View.inflate(context, R.layout.item_message,null);
            holder=new ViewHolder();
            holder.iv_unread= (ImageView) view.findViewById(R.id.iv_unread);
            holder.tv_msg_title= (TextView) view.findViewById(R.id.tv_msg_title);
            holder.tv_msg_time= (TextView) view.findViewById(R.id.tv_msg_time);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        if (i==2){
            holder.tv_msg_title.setText("医生更改了您的服务计划，快来看看吧");
        }
        if (i==3){
            holder.tv_msg_title.setText("您的计划变更申请医生已同意");
            holder.iv_unread.setVisibility(View.INVISIBLE);
        }
        return view;
    }
    public String getStrToDate(Long lo){
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy年-MM月-dd日    HH:mm");
        return sd.format(date);
    }
    class ViewHolder{
        public ImageView iv_unread;
        TextView tv_msg_title,tv_msg_time;
    }


}
