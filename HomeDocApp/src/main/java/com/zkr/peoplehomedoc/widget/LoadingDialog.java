package com.zkr.peoplehomedoc.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.zkr.peoplehomedoc.R;


/**
 * @PackageName: ---------cn.com.sinosoft.wjwapp.widget
 * @Description: ---------描述这个类的作用
 * @author: ---------------LF
 * @date: -----------------2016/9/5 11:45
 * @Copyright: -----------中科软
 */
@SuppressLint("ValidFragment")
public class LoadingDialog extends DialogFragment {

    private String showText;

    public LoadingDialog(Context context, String showText){
        this.showText=showText;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.loading_page, container);
        TextView tipTextView = (TextView) view.findViewById(R.id.tipTextView);// 提示文字
        if(!TextUtils.isEmpty(showText)){
            tipTextView.setText(showText);// 设置加载信息
        }
        return view;
    }
}
