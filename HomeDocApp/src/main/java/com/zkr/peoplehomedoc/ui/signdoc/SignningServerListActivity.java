package com.zkr.peoplehomedoc.ui.signdoc;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.base.BaseActivity;

public class SignningServerListActivity extends BaseActivity {

    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signning_server_list);
    }
    private void initPopWindow() {
        View popView = View.inflate(SignningServerListActivity.this, R.layout.pup_pay, null);
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //设置popwindow出现和消失动画
        // popupWindow.setAnimationStyle(R.style.PopMenuAnimation);
        Button bt1 = (Button) popView.findViewById(R.id.paybutton);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), SubmitOrderActivity.class);
                startActivity(intent);
            }
        });
    }
    public void showPop() {
        //设置popwindow显示位置
        // popupWindow.showAtLocation(parent, 0, x, y);
        // popupWindow.showAtLocation(view,5,0,10);
       // popupWindow.showAsDropDown(firtitle);
        //获取popwindow焦点
        popupWindow.setFocusable(true);
        //设置popwindow如果点击外面区域，便关闭。
        // popupWindow.setOutsideTouchable(false);
        popupWindow.update();
        if (popupWindow.isShowing()) {

        }
    }
}
