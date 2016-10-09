package com.zkr.peoplehomedoc.ui.signdoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.base.BaseActivity;
import com.zkr.peoplehomedoc.ui.user.MyReservationActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
                Intent intent=new Intent(getBaseContext(), SignServeSucess.class);
                startActivity(intent);
                break;
        }
    }
}
