package com.zkr.peoplehomedoc.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zkr.peoplehomedoc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe:
 * Created by ${苗}
 * on 2016/6/6.
 */

public class SexAdapter extends BaseAdapter {
    private Context context;
     private List<String> list=new ArrayList<>();
    private TextView sex;

    public SexAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= View.inflate(context, R.layout.sexitem,null);
        sex= (TextView) view.findViewById(R.id.sex);
        sex.setText(list.get(i).toString());
        return view;
    }

}
