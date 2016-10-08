package com.zkr.peoplehomedoc.ui.signdoc;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
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

public class SigningDocListActivity extends BaseActivity {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.lv_doc)
    ListView lvDoc;
    @Bind(R.id.et_search)
    EditText etSearch;

    private List<Map<String, String>> list = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signing_doc_list);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitle();
        initData();
        initWidget();
    }

    private void initWidget() {
        adapter = new MyAdapter(this);
        lvDoc.setAdapter(adapter);
        etSearch.setFocusable(false);
    }


    private void initTitle() {
        titleBar.setTitle("医生");
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
                convertView = mInflater.inflate(R.layout.signing_doc_list_item, null);
                viewHolder.tv_doc_name = (TextView) convertView.findViewById(R.id.tv_doc_name);
                viewHolder.tv_doc_dept = (TextView) convertView.findViewById(R.id.tv_doc_dept);
                viewHolder.tv_doc_desc = (TextView) convertView.findViewById(R.id.tv_doc_desc);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_doc_name.setText(list.get(position).get("name").toString());
            viewHolder.tv_doc_dept.setText(list.get(position).get("dept").toString());
            viewHolder.tv_doc_desc.setText("擅长:" + list.get(position).get("desc").toString());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivityUtil.switchTo(SigningDocListActivity.this, SigningUserActivity.class, false);
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView tv_doc_name, tv_doc_dept, tv_doc_desc;
        }
    }

    Map<String, String> map;

    private void initData() {
        map = new HashMap<>();
        map.put("name", "张菲斐");
        map.put("desc", "救治高血压、心律失常、心肌炎、心肌病、心包疾病、心脏瓣膜病、血栓...");
        map.put("dept", "心血管内科");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "党瑜华");
        map.put("desc", "冠心病、高血压、心肌病等多种疾病的诊断与处理，心源性休克、复杂心律失...");
        map.put("dept", "心血管内科");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "陈玉龙");
        map.put("desc", "对肝胆胰腺疾病的治疗有丰富的经验。近20年来对伴有焦虑抑郁情绪心身疾病...");
        map.put("dept", "消化内科");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "刘红山");
        map.put("desc", "能独立完成肝胆外科各种常规手术操作，如保留十二指肠的胰头部切除术...");
        map.put("dept", "肝胆胰腺外科");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "乔保平");
        map.put("desc", "各种泌尿外科疾病的治疗。对肾肿瘤、膀胱肿瘤等治疗有丰富的经验...");
        map.put("dept", "泌尿外科");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "秦兴雷");
        map.put("desc", "手术治疗胆管癌、门脉高压症、布加氏综合症、胰腺癌；梗阻性黄疸...");
        map.put("dept", "肝胆胰腺外科");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "程兆云");
        map.put("desc", "冠脉搭桥术，先心病、风湿性心脏瓣膜病等的外科诊治（瓣膜成形术）。");
        map.put("dept", "心血管外科");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "卢秀波");
        map.put("desc", "腹腔镜微创手术冶疗：甲状腺结节、肝硬化门脉高压脾亢、糖尿病和胆囊结石息肉...");
        map.put("dept", "甲状腺外科");
        list.add(map);
    }
}
