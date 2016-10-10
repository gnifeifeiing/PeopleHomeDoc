package com.zkr.peoplehomedoc.ui.appointmentDoctor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zkr.peoplehomedoc.MainActivity;
import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.base.BaseActivity;
import com.zkr.peoplehomedoc.utils.ActivityUtil;
import com.zkr.peoplehomedoc.widget.TitleBarUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author --------------LF
 * @Description: -------预约就诊信息页
 * @date ----------------2016/10/8 15:47
 */
public class AppointmentInfoActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.btn_submit)
    Button btnSubmit;
    @Bind(R.id.ll_detail)
    LinearLayout llDetail;
    @Bind(R.id.tv_success)
    TextView tvSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_info);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitle();
        initWidget();
    }

    private void initWidget() {
        btnSubmit.setOnClickListener(this);
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
                ActivityUtil.switchTo(AppointmentInfoActivity.this, MainActivity.class,false);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:
                llDetail.setVisibility(View.GONE);
                tvSuccess.setVisibility(View.VISIBLE);
                break;
        }

    }
}
