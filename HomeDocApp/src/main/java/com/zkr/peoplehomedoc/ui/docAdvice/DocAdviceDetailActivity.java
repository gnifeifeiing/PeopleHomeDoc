package com.zkr.peoplehomedoc.ui.docAdvice;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zkr.peoplehomedoc.MainActivity;
import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.base.BaseActivity;
import com.zkr.peoplehomedoc.utils.ActivityUtil;
import com.zkr.peoplehomedoc.widget.TitleBarUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author --------------LF
 * @Description: -------服务明细
 * @date ----------------2016/10/9 14:38
 */
public class DocAdviceDetailActivity extends BaseActivity {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    @Bind(R.id.tv_advice)
    TextView tvAdvice;
    @Bind(R.id.tv_name_date)
    TextView tvNameDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_advice_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitle();
    }

    private void initTitle() {
        titleBar.setTitle("医生建议");
        titleBar.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleBar.setRightImageOne(R.mipmap.back_home);
        titleBar.setRightButtonOneClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.switchTo(DocAdviceDetailActivity.this, MainActivity.class,false);
                finish();
            }
        });
    }
}
