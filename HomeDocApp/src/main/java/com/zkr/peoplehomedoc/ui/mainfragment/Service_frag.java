package com.zkr.peoplehomedoc.ui.mainfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.ui.docAdvice.DocAdviceDetailActivity;
import com.zkr.peoplehomedoc.ui.signdoc.Sign_Doc_Userinfo_Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Service_frag extends Fragment {

    View view;
    @Bind(R.id.listview)
    ListView listview;
    private List<Map<String, Object>> list = new ArrayList<>();
    Map<String, Object> map1 = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_doctor_frag, null);
        ButterKnife.bind(this, view);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("00", "0");
        list.add(map1);
        list.add(map1);
        list.add(map1);
        list.add(map1);
        MyAdapter adapter=new MyAdapter(getActivity());
        listview.setAdapter(adapter);
        return view;

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
                convertView = mInflater.inflate(R.layout.item_signservelist, null);
                viewHolder.tv_doc_name = (TextView) convertView.findViewById(R.id.tv_doc_name);
                viewHolder.tv_doc_dept = (TextView) convertView.findViewById(R.id.tv_doc_dept);
                viewHolder.tv_doc_desc = (TextView) convertView.findViewById(R.id.tv_doc_desc);
                viewHolder.button1= (Button) convertView.findViewById(R.id.button3);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), DocAdviceDetailActivity.class);
                    startActivity(intent);
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView tv_doc_name, tv_doc_dept, tv_doc_desc;
            Button button1;
        }
    }


}
