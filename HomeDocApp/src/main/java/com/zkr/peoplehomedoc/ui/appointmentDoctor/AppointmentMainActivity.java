package com.zkr.peoplehomedoc.ui.appointmentDoctor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.base.BaseActivity;
import com.zkr.peoplehomedoc.widget.TitleBarUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author --------------LF
 * @Description: -------预约就诊主页面
 * @date ----------------2016/10/8 11:38
 */
public class AppointmentMainActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.cb_doc_appointment)
    CheckBox cbDocAppointment;
    @Bind(R.id.ll_doc_appointment)
    LinearLayout llDocAppointment;
    @Bind(R.id.cb_doc_info)
    CheckBox cbDocInfo;
    @Bind(R.id.ll_doc_info)
    LinearLayout llDocInfo;
    @Bind(R.id.cb_doc_konw)
    CheckBox cbDocKonw;
    @Bind(R.id.ll_doc_konw)
    LinearLayout llDocKonw;
    @Bind(R.id.ll_content)
    LinearLayout llContent;

    private Fragment appointmentFrag;
    private Fragment docInfoFrag;
    private Fragment instructFrag;

    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitle();
        initModule(0);
        initWidget();
    }

    private void initWidget(){
        llDocAppointment.setOnClickListener(this);
        llDocInfo.setOnClickListener(this);
        llDocKonw.setOnClickListener(this);
    }

    private void initModule(int position) {
        FragmentManager fm = getSupportFragmentManager();
        // 获取事务
        transaction = fm.beginTransaction();
        // 隐藏事务
        hideFragment(transaction);
        switch (position){
            case 0:
                if (appointmentFrag == null) {
                    appointmentFrag = new AppointmentFrag();
                    transaction.add(R.id.ll_content, appointmentFrag);
                } else {
                    transaction.show(appointmentFrag);
                }
                seleShape(position);
                break;
            case 1:
                if (docInfoFrag == null) {
                    docInfoFrag = new DocInfoFrag();
                    transaction.add(R.id.ll_content, docInfoFrag);
                } else {
                    transaction.show(docInfoFrag);
                }
                seleShape(position);
                break;
            case 2:
                if (instructFrag == null) {
                    instructFrag = new InstructFrag();
                    transaction.add(R.id.ll_content, instructFrag);
                } else {
                    transaction.show(instructFrag);
                }
                seleShape(position);
                break;
        }
        transaction.commit();
    }

    /**
     * 隐藏事物
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (appointmentFrag != null) {
            transaction.hide(appointmentFrag);
        }
        if (docInfoFrag != null) {
            transaction.hide(docInfoFrag);
        }
        if (instructFrag != null) {
            transaction.hide(instructFrag);
        }
    }

    private void seleShape(int position){
        switch (position){
            case 0:
                cbDocAppointment.setChecked(true);
                cbDocInfo.setChecked(false);
                cbDocKonw.setChecked(false);
                break;
            case 1:
                cbDocAppointment.setChecked(false);
                cbDocInfo.setChecked(true);
                cbDocKonw.setChecked(false);
                break;
            case 2:
                cbDocAppointment.setChecked(false);
                cbDocInfo.setChecked(false);
                cbDocKonw.setChecked(true);
                break;
        }
    }

    private void initTitle() {
        titleBar.setTitle("预约就诊");
        titleBar.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //预约就诊
            case R.id.ll_doc_appointment:
                initModule(0);
                break;
            //医生信息
            case R.id.ll_doc_info:
                initModule(1);
                break;
            //预约须知
            case R.id.ll_doc_konw:
                initModule(2);
                break;
        }
    }
}
