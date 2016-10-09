package com.zkr.peoplehomedoc.ui.signdoc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.widget.TitleBarUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignDocList_Activity extends AppCompatActivity {
    @Bind(R.id.search_et_input)
    EditText searchEtInput;
    @Bind(R.id.search_in)
    ImageView searchIn;
    @Bind(R.id.list_view)
    ListView listView;
    MyAdapter adapter;
    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    private List<Map<String, Object>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_doc_list_);
        ButterKnife.bind(this);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("00", "0");
        list.add(map1);
        list.add(map1);
        list.add(map1);
        list.add(map1);
        adapter = new MyAdapter(this);
        listView.setAdapter(adapter);
        initTitle();
    }

    private void initTitle() {
        titleBar.setTitle("签约医生");
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
                convertView = mInflater.inflate(R.layout.signing_frag_list_item, null);
                viewHolder.gosignning = (LinearLayout) convertView.findViewById(R.id.gosignning);
                viewHolder.tv_doc_name = (TextView) convertView.findViewById(R.id.tv_doc_name);
                viewHolder.tv_doc_dept = (TextView) convertView.findViewById(R.id.tv_doc_dept);
                viewHolder.tv_doc_desc = (TextView) convertView.findViewById(R.id.tv_doc_desc);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.gosignning.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getBaseContext(), Sign_Doc_Userinfo_Activity.class);
                    startActivity(intent);
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView tv_doc_name, tv_doc_dept, tv_doc_desc;
            LinearLayout gosignning;
        }
    }

}
