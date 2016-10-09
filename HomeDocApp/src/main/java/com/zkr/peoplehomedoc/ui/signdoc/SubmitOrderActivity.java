package com.zkr.peoplehomedoc.ui.signdoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.base.BaseActivity;
import com.zkr.peoplehomedoc.ui.user.MyReservationActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SubmitOrderActivity extends BaseActivity {

    @Bind(R.id.submmit)
    Button submmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_order);
        ButterKnife.bind(this);
        submmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), PayActivity.class);
                startActivity(intent);
            }
        });
    }
}
