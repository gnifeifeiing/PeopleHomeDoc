package com.zkr.peoplehomedoc.ui.mainfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.ui.appointmentDoctor.AppointmentMainActivity;
import com.zkr.peoplehomedoc.ui.docAdvice.DocAdviceListActivity;
import com.zkr.peoplehomedoc.ui.mySigning.MySigningListActivity;
import com.zkr.peoplehomedoc.ui.news.NewsDetail;
import com.zkr.peoplehomedoc.ui.servicePlan.AppointmentListActivity;
import com.zkr.peoplehomedoc.ui.servicePlan.ServicePlanListActivity;
import com.zkr.peoplehomedoc.ui.user.UnreadMessagesActivity;
import com.zkr.peoplehomedoc.utils.ActivityUtil;
import com.zkr.peoplehomedoc.widget.FitListView;
import com.zkr.peoplehomedoc.widget.SlideShowView;

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
    @Bind(R.id.tv_home_appointment)
    TextView tvHomeAppointment;
    @Bind(R.id.iv_msg)
    ImageView ivMsg;
    @Bind(R.id.tv_round)
    TextView tvRound;
    @Bind(R.id.slideshowView)
    com.zkr.peoplehomedoc.widget.SlideShowView slideshowView;
    @Bind(R.id.SlideShowView)
    FrameLayout SlideShowView;
    @Bind(R.id.ll_unread_msg)
    LinearLayout llUnreadMsg;

    private List<Map<String, Object>> list = new ArrayList<>();
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
        adapter = new MyAdapter(getActivity());
        lvDocAdvice.setAdapter(adapter);
        //解决页面切换后总显示listview问题，而不显示其他view
        lvDocAdvice.setFocusable(false);

        llAppointment.setOnClickListener(this);
        llServicePlan.setOnClickListener(this);
        llDocAdvice.setOnClickListener(this);
        llMySigning.setOnClickListener(this);
        tvHomeAppointment.setOnClickListener(this);
        llUnreadMsg.setOnClickListener(this);
        ivMsg.setOnClickListener(this);
        lvDocAdvice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), NewsDetail.class);
                startActivity(intent);
            }
        });
    }

    Map<String, Object> map;

    private void initData() {
        map = new HashMap<>();
        map.put("title", "智能导诊破解大城市“医疗病”");
        map.put("content", "当用户们打开医生APP应用时，会发现北京、上海等一线城市的医生资源也已经接入了百度医生平台。");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "医生app三甲医院占全国三分之一");
        map.put("content", "截止目前，已经可以为全国29个省市超过200座城市的居民提供医疗服务，入驻医生总数接近10万，约占到了全国三甲医院总数的三分之一。");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "医生闭环服务模式受追捧");
        map.put("content", "在上周的国家卫计委例行发布会上，卫计委发言人宋树立表示，国家正在修订管理办法，旨在更好地从政策上指导互联网医疗服务。");
        list.add(map);
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
            case R.id.tv_home_appointment:
                ActivityUtil.switchTo(getActivity(), AppointmentListActivity.class, false);
                break;
            case R.id.ll_unread_msg://未读消息
                ActivityUtil.switchTo(getActivity(), UnreadMessagesActivity.class, false);
                break;
            case R.id.iv_msg://未读消息
                ActivityUtil.switchTo(getActivity(), UnreadMessagesActivity.class, false);
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
                viewHolder.content = (TextView) convertView.findViewById(R.id.tv_content);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.title.setText(list.get(position).get("title").toString());
            viewHolder.content.setText(list.get(position).get("content").toString());
            return convertView;
        }

        class ViewHolder {
            TextView title, content;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
