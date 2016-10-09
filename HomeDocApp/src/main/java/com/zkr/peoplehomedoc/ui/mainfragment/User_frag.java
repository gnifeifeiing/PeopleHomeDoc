package com.zkr.peoplehomedoc.ui.mainfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.ui.servicePlan.ServicePlanListActivity;
import com.zkr.peoplehomedoc.ui.user.MyReservationActivity;
import com.zkr.peoplehomedoc.ui.user.UnreadMessagesActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class User_frag extends Fragment {
    View view;
    @Bind(R.id.imageView4)
    ImageView imageView4;
    @Bind(R.id.username_text)
    TextView usernameText;
    @Bind(R.id.textView5)
    TextView textView5;
    @Bind(R.id.id_sum)
    TextView idSum;
    @Bind(R.id.tab_message)
    RelativeLayout tabMessage;
    @Bind(R.id.textView8)
    TextView textView8;
    @Bind(R.id.textView9)
    TextView textView9;
    @Bind(R.id.weiwanfuwu)
    RelativeLayout weiwanfuwu;
    @Bind(R.id.dangan)
    LinearLayout dangan;
    @Bind(R.id.myyuyue)
    LinearLayout myyuyue;
    @Bind(R.id.jiuyika)
    LinearLayout jiuyika;
    @Bind(R.id.jiuyika1)
    LinearLayout jiuyika1;
    @Bind(R.id.func)
    LinearLayout func;
    @Bind(R.id.about)
    LinearLayout about;
    @Bind(R.id.update)
    LinearLayout update;
    @Bind(R.id.xiugaimima)
    LinearLayout xiugaimima;
    @Bind(R.id.resiglogin)
    LinearLayout resiglogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_user_frag, null);
        ButterKnife.bind(this, view);
        tabMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), UnreadMessagesActivity.class);
                startActivity(intent);
            }
        });
        dangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MyReservationActivity.class);
                startActivity(intent);
            }
        });
        myyuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MyReservationActivity.class);
                startActivity(intent);
            }
        });
        weiwanfuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ServicePlanListActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
