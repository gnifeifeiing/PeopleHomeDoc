package com.zkr.peoplehomedoc.ui.signdoc;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.adapter.SexAdapter;
import com.zkr.peoplehomedoc.base.BaseActivity;
import com.zkr.peoplehomedoc.widget.TitleBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Sign_Doc_Userinfo_Activity extends BaseActivity {

    @Bind(R.id.sex)
    EditText sex;
    @Bind(R.id.sex2)
    RelativeLayout sex2;
    @Bind(R.id.sex1)
    TextView sex1;
    @Bind(R.id.homeaddress)
    EditText homeaddress;
    @Bind(R.id.signing)
    Button signing;
    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__doc__userinfo_);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        initEvent();
        initTitle();


    }

    private void initEvent() {
        sex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopWindow();
                showPop();
            }
        });
        signing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Signing_SucessActivity.class);

                startActivity(intent);
                finish();
            }
        });
    }

    private void initTitle() {
        titleBar.setTitle("签约医生");
        titleBar.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initPopWindow() {
        View popView = View.inflate(this, R.layout.pup_listview, null);
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //设置popwindow出现和消失动画
        // popupWindow.setAnimationStyle(R.style.PopMenuAnimation);
        ListView listview = (ListView) popView.findViewById(R.id.listView8);
        List<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        SexAdapter adapter1 = new SexAdapter(this, list);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, list);
        listview.setAdapter(adapter1);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    sex.setText("男");
                } else {
                    sex.setText("女");
                }
                popupWindow.dismiss();

            }
        });


    }

    public void showPop() {
        //设置popwindow显示位置
        // popupWindow.showAtLocation(parent, 0, x, y);
        // popupWindow.showAtLocation(view,5,0,10);
        popupWindow.showAsDropDown(sex);
        //获取popwindow焦点
        popupWindow.setFocusable(true);
        //设置popwindow如果点击外面区域，便关闭。
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();

    }
}
