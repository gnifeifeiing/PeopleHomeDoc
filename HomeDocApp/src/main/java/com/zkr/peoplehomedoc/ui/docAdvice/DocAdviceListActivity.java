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
        map.put("title", "牙齿美白又快又好的方法");
        map.put("content", "冷光美白是一项正流行于欧美的最新的牙齿美白技术，它不仅可以去除牙齿表面的色素沉积，同时可进入牙齿深层达到脱色的效果。");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "如何改善失眠");
        map.put("content", "建议你积极服用谷维素+刺五加+B1片来调理,注意营养和休息保持心情愉悦,如有什么不明白的,欢迎你再次提问,我们会对你的问题密切关注.");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "炎支原体抗体(mPAd)+1:160");
        map.put("content", "在药物治疗上以大环内酯类抗生素如罗红霉素等药物为主,这个疾病是可以治愈的只是需要用药的时间比较长,在用抗生素的同时可以根据辨证用中药进行辅助治疗效果是不错的");
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
            viewHolder.tv_desc.setText(Html.fromHtml("<span><B>医生建议:</B>"+list.get(position).get("content").toString()+"</span>"));
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
