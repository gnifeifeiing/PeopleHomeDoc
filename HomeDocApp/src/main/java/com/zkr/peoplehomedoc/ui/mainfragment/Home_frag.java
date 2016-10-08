package com.zkr.peoplehomedoc.ui.mainfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.widget.FitListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


public class Home_frag extends Fragment {

    View view;

    @Bind(R.id.lv_doc_advice)
    FitListView lvDocAdvice;

    private List<String> list=new ArrayList<>();
    private MyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home_frag, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        initData();
        initWidget();
    }

    private void initWidget() {
        adapter=new MyAdapter(getActivity());
        lvDocAdvice.setAdapter(adapter);
        //解决页面切换后总显示listview问题，而不显示其他view
        lvDocAdvice.setFocusable(false);
    }

    private void initData() {
        list.add("坚持每日散步1-1.5小时，放慢生活、工作节奏，更多的感受快乐。");
        list.add("适当减少肉食的摄入量，以每日不超过4两（200g）为宜；");
        list.add("坚持带太阳眼镜对保护你的眼睛远离白内障等各种眼疾非常重要。");
        list.add("别在身体感到疲劳时喝咖啡或浓茶来提神，会对心血管系统造成伤害。");
        list.add("一年染发4次以上，出现头晕、偏头痛，促使血小板降低。");
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
                convertView = mInflater.inflate(R.layout.home_list_item, null);
                viewHolder.title = (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.title.setText(list.get(position).toString());
            return convertView;
        }

        class ViewHolder {
            TextView title;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
