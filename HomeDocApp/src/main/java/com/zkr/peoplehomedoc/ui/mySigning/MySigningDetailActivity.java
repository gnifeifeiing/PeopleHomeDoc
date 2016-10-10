package com.zkr.peoplehomedoc.ui.mySigning;

import android.os.Bundle;
import android.view.View;

import com.zkr.peoplehomedoc.MainActivity;
import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.base.BaseActivity;
import com.zkr.peoplehomedoc.utils.ActivityUtil;
import com.zkr.peoplehomedoc.widget.TitleBarUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author --------------LF
 * @Description: -------我的签约详情
 * @date ----------------2016/10/9 17:19
 */
public class MySigningDetailActivity extends BaseActivity {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_signing_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitle();
    }

    private void initTitle() {
        titleBar.setTitle("签约明细");
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
                ActivityUtil.switchTo(MySigningDetailActivity.this, MainActivity.class,false);
                finish();
            }
        });
    }
}
