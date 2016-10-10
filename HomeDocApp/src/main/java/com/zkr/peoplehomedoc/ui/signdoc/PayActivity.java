package com.zkr.peoplehomedoc.ui.signdoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.base.BaseActivity;
import com.zkr.peoplehomedoc.widget.TitleBarUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PayActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.pay)
    Button pay;
    @Bind(R.id.radiobutton1)
    RadioButton radiobutton1;
    @Bind(R.id.radiobutton2)
    RadioButton radiobutton2;
    @Bind(R.id.radiobutton3)
    RadioButton radiobutton3;
    @Bind(R.id.radiobutton4)
    RadioButton radiobutton4;
    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        initTitle();
        radiobutton1.setOnClickListener(this);
        radiobutton2.setOnClickListener(this);
        radiobutton3.setOnClickListener(this);
        radiobutton4.setOnClickListener(this);
        pay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radiobutton1:
                radiobutton2.setChecked(false);
                radiobutton3.setChecked(false);
                radiobutton4.setChecked(false);
                break;
            case R.id.radiobutton2:
                radiobutton1.setChecked(false);
                radiobutton3.setChecked(false);
                radiobutton4.setChecked(false);
                break;
            case R.id.radiobutton3:
                radiobutton2.setChecked(false);
                radiobutton1.setChecked(false);
                radiobutton4.setChecked(false);
                break;
            case R.id.radiobutton4:
                radiobutton2.setChecked(false);
                radiobutton3.setChecked(false);
                radiobutton1.setChecked(false);
                break;
            case R.id.pay:
                Intent intent = new Intent(getBaseContext(), SignServeSucess.class);
                startActivity(intent);
                break;
        }
    }

    private void initTitle() {
        titleBar.setTitle("签约服务");
        titleBar.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
