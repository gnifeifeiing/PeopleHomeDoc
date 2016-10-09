package com.zkr.peoplehomedoc.ui.mainfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.ui.appointmentDoctor.AppointmentMainActivity;
import com.zkr.peoplehomedoc.ui.docAdvice.DocAdviceListActivity;
import com.zkr.peoplehomedoc.ui.mySigning.MySigningListActivity;
import com.zkr.peoplehomedoc.ui.servicePlan.ServicePlanListActivity;
import com.zkr.peoplehomedoc.utils.ActivityUtil;
import com.zkr.peoplehomedoc.widget.FitListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


public class Home_frag extends Fragment implements View.OnClickListener {

    View view;

    @Bind(R.id.lv_doc_advice)
    FitListView lvDocAdvice;
    @Bind(R.id.ll_appointment)
    LinearLayout llAppointment;
    @Bind(R.id.ll_service_plan)
    LinearLayout llServicePlan;
    @Bind(R.id.ll_doc_advice)
    LinearLayout llDocAdvice;
    @Bind(R.id.ll_my_signing)
    LinearLayout llMySigning;

    private List<String> list=new ArrayList<>();
    private MyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home_frag, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        initData();
        initWidget();
    }

    private void initWidget() {
        adapter=new MyAdapter(getActivity());
        lvDocAdvice.setAdapter(adapter);
        //解决页面切换后总显示listview问题，而不显示其他view
        lvDocAdvice.setFocusable(false);

        llAppointment.setOnClickListener(this);
        llServicePlan.setOnClickListener(this);
        llDocAdvice.setOnClickListener(this);
        llMySigning.setOnClickListener(this);
    }

    private void initData() {
        list.add("让我们人人参与三减，努力实现三健");
        list.add("让我们人人参与三减，努力实现三健");
        list.add("让我们人人参与三减，努力实现三健");
        list.add("让我们人人参与三减，努力实现三健");
        list.add("让我们人人参与三减，努力实现三健");
        list.add("让我们人人参与三减，努力实现三健");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_appointment:
                ActivityUtil.switchTo(getActivity(), AppointmentMainActivity.class, false);
                break;
            case R.id.ll_service_plan:
                ActivityUtil.switchTo(getActivity(), ServicePlanListActivity.class, false);
                break;
            case R.id.ll_doc_advice:
                ActivityUtil.switchTo(getActivity(), DocAdviceListActivity.class, false);
                break;
            case R.id.ll_my_signing:
                ActivityUtil.switchTo(getActivity(), MySigningListActivity.class, false);
                break;
        }
    }


    private class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public MyAdapter(Context context) {
            // TODO Auto-generated constructor stub
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.home_list_item, null);
                viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.title.setText(list.get(position).toString());
            return convertView;
        }

        class ViewHolder {
            TextView title;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
