package com.zkr.peoplehomedoc.ui.servicePlan;

import android.os.Bundle;
import android.view.View;

import com.zkr.peoplehomedoc.MainActivity;
import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.base.BaseActivity;
import com.zkr.peoplehomedoc.utils.ActivityUtil;
import com.zkr.peoplehomedoc.widget.TitleBarUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AppointmentSucActivity extends BaseActivity {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_suc);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitle();
    }

    private void initTitle() {
        titleBar.setTitle("预约就诊");
        titleBar.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleBar.setRightImageOne(R.mipmap.back_home);
        titleBar.setRightButtonOneClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.switchTo(AppointmentSucActivity.this, MainActivity.class,false);
                finish();
            }
        });
    }
}
