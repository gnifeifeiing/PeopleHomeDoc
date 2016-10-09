package com.zkr.peoplehomedoc.ui.docAdvice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.zkr.peoplehomedoc.ui.servicePlan.AppointmentSucActivity;
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
 * @Description: -------医生建议列表
 * @date ----------------2016/10/9 14:10
 */
public class DocAdviceListActivity extends BaseActivity {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.lv_advice)
    ListView lvAdvice;

    private List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_advice_list);
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
        map.put("title", "高血压管理包   季度随访一次");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "高血压管理包   季度随访一次");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "高血压管理包   季度随访一次");
        list.add(map);

    }

    private void initWidget() {
        MyAdapter adapter = new MyAdapter(this);
        lvAdvice.setAdapter(adapter);
    }

    private void initTitle() {
        titleBar.setTitle("医生建议");
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
                convertView = mInflater.inflate(R.layout.doc_advice_list_item, null);
                viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                viewHolder.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
                viewHolder.tv_name_date = (TextView) convertView.findViewById(R.id.tv_name_date);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_title.setText(list.get(position).get("title").toString());
            viewHolder.tv_desc.setText(Html.fromHtml("<span><B>医生建议:</B>坚持每日散步1-1.5小时，放慢生活、工作节奏，保持快乐的心情。适当减少肉食的摄入量，以每日不超过4两（200g）为宜。</span>"));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtil.switchTo(DocAdviceListActivity.this,DocAdviceDetailActivity.class,false);
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView tv_title,tv_desc,tv_name_date;
        }
    }
}
