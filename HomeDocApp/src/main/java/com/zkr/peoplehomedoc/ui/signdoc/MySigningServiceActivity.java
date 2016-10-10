package com.zkr.peoplehomedoc.ui.signdoc;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.base.BaseActivity;
import com.zkr.peoplehomedoc.utils.ActivityUtil;
import com.zkr.peoplehomedoc.utils.ConvertDpAndPx;
import com.zkr.peoplehomedoc.widget.TitleBarUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author --------------LF
 * @Description: -------签约服务
 * @date ----------------2016/10/10 9:27
 */
public class MySigningServiceActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.tv_search)
    TextView tvSearch;
    @Bind(R.id.tv_recommend)
    TextView tvRecommend;
    @Bind(R.id.cb_recommend)
    CheckBox cbRecommend;
    @Bind(R.id.ll_recommend)
    LinearLayout llRecommend;
    @Bind(R.id.tv_base)
    TextView tvBase;
    @Bind(R.id.cb_base)
    CheckBox cbBase;
    @Bind(R.id.ll_base)
    LinearLayout llBase;
    @Bind(R.id.tv_features)
    TextView tvFeatures;
    @Bind(R.id.cb_features)
    CheckBox cbFeatures;
    @Bind(R.id.ll_features)
    LinearLayout llFeatures;
    @Bind(R.id.lv_service)
    ListView lvService;

    private List<Map<String, Object>> recommendList = new ArrayList<>();
    private List<Map<String, Object>> baseList = new ArrayList<>();
    private List<Map<String, Object>> featuresList = new ArrayList<>();
    //0:推荐服务包 1:基础服务包 2:特色服务包
    private int serviceType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_signing_service);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitle();
        initData();
        initWidget();
        initListener();
    }

    private void initListener() {
        llRecommend.setOnClickListener(this);
        llBase.setOnClickListener(this);
        llFeatures.setOnClickListener(this);
    }


    private void initWidget() {
        if(serviceType==0){
            MyAdapter adapter = new MyAdapter(this, recommendList);
            lvService.setAdapter(adapter);
        }else if(serviceType==1){
            MyAdapter adapter = new MyAdapter(this, baseList);
            lvService.setAdapter(adapter);
        }else{
            MyAdapter adapter = new MyAdapter(this, featuresList);
            lvService.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //推荐服务包
            case R.id.ll_recommend:
                cbRecommend.setChecked(true);
                cbBase.setChecked(false);
                cbFeatures.setChecked(false);
                serviceType=0;
                initWidget();
                break;
            //基础服务包
            case R.id.ll_base:
                cbRecommend.setChecked(false);
                cbBase.setChecked(true);
                cbFeatures.setChecked(false);
                serviceType=1;
                initWidget();
                break;
            //特色服务包
            case R.id.ll_features:
                cbRecommend.setChecked(false);
                cbBase.setChecked(false);
                cbFeatures.setChecked(true);
                serviceType=2;
                initWidget();
                break;
        }
    }

    private class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private List<Map<String, Object>> lists;

        public MyAdapter(Context context, List<Map<String, Object>> myList) {
            // TODO Auto-generated constructor stub
            mInflater = LayoutInflater.from(context);
            this.lists = myList;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return lists.get(position);
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
                convertView = mInflater.inflate(R.layout.signing_service_package_list_item, null);
                viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                viewHolder.tv_fee = (TextView) convertView.findViewById(R.id.tv_fee);
                viewHolder.ll_type = (LinearLayout) convertView.findViewById(R.id.ll_type);
                viewHolder.tv_del = (TextView) convertView.findViewById(R.id.tv_del);
                viewHolder.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
                viewHolder.tv_add = (TextView) convertView.findViewById(R.id.tv_add);
                viewHolder.ll_content = (LinearLayout) convertView.findViewById(R.id.ll_content);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tv_title.setText(lists.get(position).get("title").toString());
            if (lists.get(position).get("type").toString().equals("0")) {
                viewHolder.tv_fee.setText("免费");
                viewHolder.ll_type.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.tv_fee.setText(lists.get(position).get("price").toString()+"元");
                viewHolder.ll_type.setVisibility(View.VISIBLE);
            }
            String[] ss = lists.get(position).get("content").toString().split("#");
            for (int i = 0; i < ss.length; i++) {
                TextView tv = new TextView(MySigningServiceActivity.this);
                tv.setText("☆" + ss[i]);
                viewHolder.ll_content.addView(tv);
            }
            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.tv_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(Integer.parseInt(finalViewHolder.tv_num.getText().toString())>0){
                       finalViewHolder.tv_num.setText(Integer.parseInt(finalViewHolder.tv_num.getText().toString())-1+"");
                   }
                    showPopupWindow(position,Integer.parseInt(finalViewHolder.tv_num.getText().toString()));
                }
            });
            viewHolder.tv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalViewHolder.tv_num.setText(Integer.parseInt(finalViewHolder.tv_num.getText().toString())+1+"");
                    showPopupWindow(position,Integer.parseInt(finalViewHolder.tv_num.getText().toString()));
                }
            });

            return convertView;
        }

        View contentView;
        TextView tv_submit;
        LinearLayout ll_content;
        TextView tv_total;
        List<Integer> savePosition=new ArrayList<>();
        Map<Integer,Integer> savePositionNum=new HashMap<>();
        int total=0;
        private void showPopupWindow(int position,int num) {
            if(!savePosition.contains(position)){
                savePosition.add(position);
            }
            savePositionNum.put(position,num);
            // 一个自定义的布局，作为显示的内容
            if(contentView==null){
                contentView = LayoutInflater.from(MySigningServiceActivity.this).inflate(
                        R.layout.my_signing_service_shopping_popwindow, null);
                final PopupWindow popupWindow = new PopupWindow(contentView,
                        ConvertDpAndPx.Dp2Px(MySigningServiceActivity.this,200), ConvertDpAndPx.Dp2Px(MySigningServiceActivity.this,220), true);
                // 设置按钮的点击事件
                tv_submit = (TextView) contentView.findViewById(R.id.btn_submit);
                ll_content=(LinearLayout) contentView.findViewById(R.id.ll_content);
                tv_total=(TextView) contentView.findViewById(R.id.tv_total);
                popupWindow.setFocusable(false);
                // 设置好参数之后再show
                popupWindow.showAtLocation(MySigningServiceActivity.this.findViewById(R.id.ll_all), Gravity.BOTTOM|Gravity.LEFT, 0, 0);
            }
            ll_content.removeAllViews();
            for(int i=0;i<savePosition.size();i++){
                String title=lists.get(savePosition.get(i)).get("title").toString()+"*"+savePositionNum.get(savePosition.get(i));
                TextView tv=new TextView(MySigningServiceActivity.this);
                tv.setText(title);
                int price=Integer.parseInt(lists.get(savePosition.get(i)).get("price").toString());
                total=price*Integer.parseInt(savePositionNum.get(savePosition.get(i)).toString());
                ll_content.addView(tv);
            }
            tv_total.setText("共计："+total+"元");
            tv_submit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    ActivityUtil.switchTo(MySigningServiceActivity.this,SubmitOrderActivity.class,false);
                }
            });


        }


        class ViewHolder {
            TextView tv_title, tv_fee, tv_del, tv_num, tv_add;
            LinearLayout ll_type, ll_content;
        }
    }

    private void initTitle() {
        titleBar.setTitle("签约服务");
        titleBar.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    Map<String, Object> map;

    private void initData() {
        map = new HashMap<>();
        map.put("title", "高血压管理包");
        map.put("content", "35岁以上常住居民，免费享受高血压筛查1次#原发性高血压患者，每年享受至少4次面对面的随访#原发性高血压患者，每年享受1次健康检查。");
        map.put("type", "0");
        map.put("price", "39");
        recommendList.add(map);
        baseList.add(map);

        map = new HashMap<>();
        map.put("title", "中医保健包");
        map.put("content", "享受刮痧、拔罐、理疗一次#自购买之日内3月有效，购买多次有效期累计延长。");
        map.put("type", "1");
        map.put("price", "39");
        recommendList.add(map);
        featuresList.add(map);

        map = new HashMap<>();
        map.put("title", "家庭病床包");
        map.put("content", "医生上门换药、理疗，为家属提供指导#每次服务时长不小于1小时#自购买之日内3月有效，购买多次有效期累计延长");
        map.put("type", "1");
        map.put("price", "69");
        recommendList.add(map);
        featuresList.add(map);
    }


}
