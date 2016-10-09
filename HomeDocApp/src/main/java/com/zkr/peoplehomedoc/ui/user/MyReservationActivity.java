package com.zkr.peoplehomedoc.ui.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.base.BaseActivity;
import com.zkr.peoplehomedoc.ui.signdoc.Sign_Doc_Userinfo_Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyReservationActivity extends BaseActivity {

    @Bind(R.id.listview)
    ListView listview;
    private List<Map<String, Object>> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reservation);
        ButterKnife.bind(this);
        MyAdapter adapter=new MyAdapter(this);
        list.add(new HashMap<String, Object>());
        list.add(new HashMap<String, Object>());
        listview.setAdapter(adapter);

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
                convertView = mInflater.inflate(R.layout.item_reservation, null);
                 convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }

        class ViewHolder {
            TextView tv_doc_name, tv_doc_dept, tv_doc_desc;
            LinearLayout gosignning;
        }
    }

}
