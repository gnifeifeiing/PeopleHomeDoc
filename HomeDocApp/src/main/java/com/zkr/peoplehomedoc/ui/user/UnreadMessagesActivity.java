package com.zkr.peoplehomedoc.ui.user;

import android.os.Bundle;
import android.widget.ListView;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.base.BaseActivity;

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
    }
}
