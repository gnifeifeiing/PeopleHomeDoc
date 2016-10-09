package com.zkr.peoplehomedoc.ui.mySigning;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
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
 * @Description: -------我的签约列表
 * @date ----------------2016/10/9 16:51
 */
public class MySigningListActivity extends BaseActivity {


    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.lv_signing)
    ListView lvSigning;
    private List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_signing_list);
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
        map.put("title", "某某卫生院   张敏瑞医生");
        map.put("time", "签约时间：2016-10-12 10:02:00");
        map.put("state", "当前状态：未缴费");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "某某卫生院   张敏瑞医生");
        map.put("time", "签约时间：2016-10-01 14:25:00");
        map.put("state", "当前状态：已缴费");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "某某卫生院   张敏瑞医生");
        map.put("time", "签约时间：2016-09-21 10:02:00");
        map.put("state", "当前状态：已完成");
        list.add(map);

    }

    private void initWidget() {
        MyAdapter adapter = new MyAdapter(this);
        lvSigning.setAdapter(adapter);
    }

    private void initTitle() {
        titleBar.setTitle("我的签约");
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
                convertView = mInflater.inflate(R.layout.my_signing_list_item, null);
                viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
                viewHolder.tv_state = (TextView) convertView.findViewById(R.id.tv_state);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_title.setText(list.get(position).get("title").toString());
            viewHolder.tv_date.setText(list.get(position).get("time").toString());
            viewHolder.tv_state.setText(list.get(position).get("state").toString());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtil.switchTo(MySigningListActivity.this,MySigningDetailActivity.class,false);
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView tv_title, tv_date, tv_state;
        }
    }
}
