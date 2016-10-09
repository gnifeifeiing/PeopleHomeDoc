package com.zkr.peoplehomedoc.ui.servicePlan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class AppointmentListActivity extends BaseActivity {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.lv_plan)
    ListView lvPlan;
    
    private List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);
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
        list = new ArrayList<>();
        map = new HashMap<>();
        map.put("time", "2016-10-19  星期三  上午");
        list.add(map);

        map = new HashMap<>();
        map.put("time", "2016-10-20  星期四  上午");
        list.add(map);

        map = new HashMap<>();
        map.put("time", "2016-10-21  星期五  上午");
        list.add(map);

        map = new HashMap<>();
        map.put("time", "2016-10-22  星期六  上午");
        list.add(map);

        map = new HashMap<>();
        map.put("time", "2016-10-23  星期日  上午");
        list.add(map);

        map = new HashMap<>();
        map.put("time", "2016-10-24  星期一  上午");
        list.add(map);

    }

    private void initWidget() {
        MyAdapter adapter = new MyAdapter(this);
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
                convertView = mInflater.inflate(R.layout.appointment_list_item, null);
                viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                viewHolder.tv_btn = (TextView) convertView.findViewById(R.id.tv_btn);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_time.setText(list.get(position).get("time").toString());
            viewHolder.tv_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder =new AlertDialog.Builder(AppointmentListActivity.this);
                    builder.setTitle("");
                    builder.setMessage("是否确定预约？");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityUtil.switchTo(AppointmentListActivity.this,AppointmentSucActivity.class,false);
                        }
                    });
                    builder.show();
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView  tv_time, tv_btn;
        }
    }
}
