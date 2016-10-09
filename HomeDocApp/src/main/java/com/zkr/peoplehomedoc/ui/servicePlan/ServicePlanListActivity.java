package com.zkr.peoplehomedoc.ui.servicePlan;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.base.BaseActivity;
import com.zkr.peoplehomedoc.utils.ActivityUtil;
import com.zkr.peoplehomedoc.widget.TitleBarUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author --------------LF
 * @Description: -------服务计划列表页
 * @date ----------------2016/10/8 16:38
 */
public class ServicePlanListActivity extends BaseActivity {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.lv_plan)
    ListView lvPlan;

    private List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_plan_list);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitle();
        initData();
        initWidget();
    }

    Map<String, Object> map;
    private void initData() {
        list=new ArrayList<>();
        map = new HashMap<>();
        map.put("title", "中医保健包    刮痧、拔罐一次");
        map.put("time", "预约服务时间：未设置");
        map.put("state", "0");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "高血压管理包   季度随访一次");
        map.put("time", "预约服务时间：2015-9-30 下午");
        map.put("state", "1");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "中医保健包    刮痧、拔罐一次");
        map.put("time", "预约服务时间：2015-10-3 下午");
        map.put("state", "1");
        list.add(map);

    }

    private void initWidget() {
        MyAdapter adapter=new MyAdapter(this);
        lvPlan.setAdapter(adapter);
    }

    private void initTitle() {
        titleBar.setTitle("服务计划");
        titleBar.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                convertView = mInflater.inflate(R.layout.service_plan_list_item, null);
                viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                viewHolder.tv_btn = (TextView) convertView.findViewById(R.id.tv_btn);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_title.setText(list.get(position).get("title").toString());
            viewHolder.tv_time.setText(list.get(position).get("time").toString());
            if(list.get(position).get("state").toString().equals("0")){
                viewHolder.tv_btn.setText("预约");
                viewHolder.tv_btn.setBackgroundColor(Color.parseColor("#90cadd"));
            }else{
                viewHolder.tv_btn.setText("改约");
                viewHolder.tv_btn.setBackgroundColor(Color.parseColor("#9acd6b"));
            }
            viewHolder.tv_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtil.switchTo(ServicePlanListActivity.this,AppointmentListActivity.class,false);
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView tv_title,tv_time,tv_btn;
        }
    }

}
