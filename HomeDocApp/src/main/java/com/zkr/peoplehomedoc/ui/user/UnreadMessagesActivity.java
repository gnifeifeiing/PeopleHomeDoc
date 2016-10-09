package com.zkr.peoplehomedoc.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.base.BaseActivity;
import com.zkr.peoplehomedoc.ui.servicePlan.ServicePlanListActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UnreadMessagesActivity extends BaseActivity {

    @Bind(R.id.listView6)
    ListView listView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unread_messages);
        ButterKnife.bind(this);
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("1","1");
        list.add(map);
        list.add(map);
        list.add(map);
        list.add(map);
        MessageAdapter adapter = new MessageAdapter(list, UnreadMessagesActivity.this);
        listView6.setAdapter(adapter);
        listView6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getBaseContext(), ServicePlanListActivity.class);
                startActivity(intent);
            }
        });
    }
}
