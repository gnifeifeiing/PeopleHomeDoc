package com.zkr.peoplehomedoc.ui.mainfragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.ui.signdoc.SigningDocListActivity;
import com.zkr.peoplehomedoc.utils.ActivityUtil;
import com.zkr.peoplehomedoc.widget.FitListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Signing_frag extends Fragment implements View.OnClickListener {

    View view;
    @Bind(R.id.lv_doc)
    FitListView lvDoc;
    @Bind(R.id.btn_signing)
    Button btnSigning;

    private List<Map<String, String>> list = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_signing_frag, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        initData();
        initWidget();
        initListener();
    }

    private void initListener() {
        btnSigning.setOnClickListener(this);
    }

    private void initWidget() {
        adapter = new MyAdapter(getActivity());
        lvDoc.setAdapter(adapter);
        //解决页面切换后总显示listview问题，而不显示其他view
        lvDoc.setFocusable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signing:
                Intent intent=new Intent(getActivity(),SigningDocListActivity.class);
                startActivity(intent);
//                ActivityUtil.switchTo(getActivity(), SigningDocListActivity.class,false);
                break;
            default:
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
                convertView = mInflater.inflate(R.layout.signing_frag_list_item, null);
                viewHolder.tv_doc_name = (TextView) convertView.findViewById(R.id.tv_doc_name);
                viewHolder.tv_doc_dept = (TextView) convertView.findViewById(R.id.tv_doc_dept);
                viewHolder.tv_doc_desc = (TextView) convertView.findViewById(R.id.tv_doc_desc);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_doc_name.setText(list.get(position).get("name").toString());
            viewHolder.tv_doc_dept.setText(list.get(position).get("dept").toString());
            viewHolder.tv_doc_desc.setText(list.get(position).get("desc").toString());
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
        map.put("desc", "救治高血压、冠心病、心肌梗死、心力衰竭、心律失常、心肌炎、心肌病、心包疾病、心脏瓣膜病、血栓...");
        map.put("dept", "郑大第一附属医院  心血管内科");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "党瑜华");
        map.put("desc", "冠心病、高血压、心肌病等多种疾病的诊断与处理，急性心肌梗死、心力衰竭、心源性休克、复杂心律失...");
        map.put("dept", "郑大第一附属医院  心血管内科");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "陈玉龙");
        map.put("desc", "对食管、胃肠、肝胆胰腺疾病的治疗有丰富的经验。近20年来对伴有焦虑抑郁情绪的消化科心身疾病有较...");
        map.put("dept", "郑大第一附属医院  消化内科");
        list.add(map);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
