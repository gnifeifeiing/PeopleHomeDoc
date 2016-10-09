package com.zkr.peoplehomedoc.ui.appointmentDoctor;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.utils.ActivityUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Description: -------预约就诊
 * @author --------------LF
 * @date ----------------2016/10/8 15:45
 */
public class AppointmentFrag extends Fragment {

    View view;
    @Bind(R.id.lv)
    ListView lv;

    private List<Map<String, Object>> lists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_appointment_frag, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        initData();
        initWidget();
    }

    private void initWidget() {
        MyAdapter adapter = new MyAdapter(getActivity());
        lv.setAdapter(adapter);
    }

    Map<String, Object> map;

    private void initData() {
        lists = new ArrayList<>();
        map = new HashMap<>();
        map.put("date", "2016-10-19");
        map.put("week", "（星期三）");
        map.put("amState", false);
        map.put("pmState", false);
        lists.add(map);

        map = new HashMap<>();
        map.put("date", "2016-10-20");
        map.put("week", "（星期四）");
        map.put("amState", false);
        map.put("pmState", true);
        lists.add(map);

        map = new HashMap<>();
        map.put("date", "2016-10-21");
        map.put("week", "（星期五）");
        map.put("amState", true);
        map.put("pmState", false);
        lists.add(map);

        map = new HashMap<>();
        map.put("date", "2016-10-22");
        map.put("week", "（星期六）");
        map.put("amState", true);
        map.put("pmState", true);
        lists.add(map);

        map = new HashMap<>();
        map.put("date", "2016-10-23");
        map.put("week", "（星期日）");
        map.put("amState", true);
        map.put("pmState", false);
        lists.add(map);

        map = new HashMap<>();
        map.put("date", "2016-10-24");
        map.put("week", "（星期一）");
        map.put("amState", false);
        map.put("pmState", false);
        lists.add(map);

        map = new HashMap<>();
        map.put("date", "2016-10-25");
        map.put("week", "（星期二）");
        map.put("amState", true);
        map.put("pmState", true);
        lists.add(map);

        map = new HashMap<>();
        map.put("date", "2016-10-26");
        map.put("week", "（星期三）");
        map.put("amState", true);
        map.put("pmState", true);
        lists.add(map);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return lists.get(position);
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
                convertView = mInflater.inflate(R.layout.appointment_frag_list_item, null);
                viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
                viewHolder.tv_week = (TextView) convertView.findViewById(R.id.tv_week);
                viewHolder.tv_am = (TextView) convertView.findViewById(R.id.tv_am);
                viewHolder.tv_pm = (TextView) convertView.findViewById(R.id.tv_pm);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_date.setText(lists.get(position).get("date").toString());
            viewHolder.tv_week.setText(lists.get(position).get("week").toString());
            if((boolean)lists.get(position).get("amState")){
                viewHolder.tv_am.setBackgroundColor(Color.parseColor("#9acd6b"));
            }else{
                viewHolder.tv_am.setBackgroundColor(Color.parseColor("#cccccc"));
            }

            viewHolder.tv_am.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((boolean)lists.get(position).get("amState")){
                        ActivityUtil.switchTo(getActivity(),AppointmentInfoActivity.class,false);
                    }
                }
            });

            if((boolean)lists.get(position).get("pmState")){
                viewHolder.tv_pm.setBackgroundColor(Color.parseColor("#9acd6b"));
            }else{
                viewHolder.tv_pm.setBackgroundColor(Color.parseColor("#cccccc"));
            }

            viewHolder.tv_pm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if((boolean)lists.get(position).get("pmState")){
                        ActivityUtil.switchTo(getActivity(),AppointmentInfoActivity.class,false);
                    }
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView tv_date, tv_week, tv_am, tv_pm;
        }
    }
}
