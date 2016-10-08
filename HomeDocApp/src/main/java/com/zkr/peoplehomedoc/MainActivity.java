package com.zkr.peoplehomedoc;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zkr.peoplehomedoc.base.BaseActivity;
import com.zkr.peoplehomedoc.ui.mainfragment.Chat_frag;
import com.zkr.peoplehomedoc.ui.mainfragment.Home_frag;
import com.zkr.peoplehomedoc.ui.mainfragment.Service_frag;
import com.zkr.peoplehomedoc.ui.mainfragment.Signing_frag;
import com.zkr.peoplehomedoc.ui.mainfragment.User_frag;


/**
 * @Description: -------首页父activity
 * @author --------------LF
 * @date ----------------2016/6/29 9:39
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Fragment mHomeFragment;
    private Fragment mServiceFragment;
    private Fragment mSigningFragment;
    private Fragment mChatFragment;
    private Fragment mUserFragment;
    private ImageView homeImg, serviceImg, signingImg, chatImg,userImg;
    private TextView homeTv, serviceTv, signingTv, chatTv,userTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        homeImg = (ImageView) findViewById(R.id.tab_home_image);
        serviceImg = (ImageView) findViewById(R.id.tab_service_image);
        signingImg = (ImageView) findViewById(R.id.tab_signing_image);
        chatImg = (ImageView) findViewById(R.id.tab_chat_image);
        userImg = (ImageView) findViewById(R.id.tab_user_image);
        homeTv = (TextView) findViewById(R.id.tab_home_text);
        serviceTv = (TextView) findViewById(R.id.tab_service_text);
        signingTv = (TextView) findViewById(R.id.tab_signing_text);
        chatTv = (TextView) findViewById(R.id.tab_chat_text);
        userTv = (TextView) findViewById(R.id.tab_user_text);
        LinearLayout l1 = (LinearLayout) findViewById(R.id.tab_home);
        LinearLayout l2 = (LinearLayout) findViewById(R.id.tab_service);
        LinearLayout l3 = (LinearLayout) findViewById(R.id.tab_signing);
        LinearLayout l4 = (LinearLayout) findViewById(R.id.tab_chat);
        LinearLayout l5 = (LinearLayout) findViewById(R.id.tab_user);
        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);
        l5.setOnClickListener(this);
        select(0);
        if (MyApplication.loginFlag){

        }
        if (getIntent().getIntExtra("postion", 0) != 0) {
            select(getIntent().getIntExtra("postion", 0));
        } else if (getIntent().getIntExtra("postion", 1) == 0) {
            select(0);
        }
    }

    /**
     * @Description:        显示对应得fragment
     * @author               miao
     * @date                 2016/4/28 11:15
     */
    private void select(int position) {
        // 获取FragmentManager对象
        // FragmentManager fm=getFragmentManager();
        FragmentManager fm = getSupportFragmentManager();
        // 获取事务
        FragmentTransaction transaction = fm.beginTransaction();
        // 隐藏事务
        hideFragment(transaction);
        // 根据传递过来的参数 选择显示对应的Fragment
        switch (position) {
            case 0:// HomeFragment
                // 如果此Fragment为空 就新建一个
                if (mHomeFragment == null) {
                    mHomeFragment = new Home_frag();
                    transaction.add(R.id.cccc, mHomeFragment);
                } else {// 此Fragment已经存在 直接显示
                    transaction.show(mHomeFragment);
                }
                // 改变按下后图片和文字的状态
                changeStatus(0);
                break;
            case 1:
                if (mServiceFragment == null) {
                    mServiceFragment = new Service_frag();

                    transaction.add(R.id.cccc, mServiceFragment);
                } else {
                    transaction.show(mServiceFragment);
                }
                changeStatus(1);
                break;
            case 2:
                if (mSigningFragment == null) {
                    mSigningFragment = new Signing_frag();
                    transaction.add(R.id.cccc, mSigningFragment);
                } else {
                    transaction.show(mSigningFragment);
                }
                changeStatus(2);
                break;
            case 3:
                if (mChatFragment == null) {
                    mChatFragment = new Chat_frag();
                    transaction.add(R.id.cccc, mChatFragment);
                } else {
                    transaction.show(mChatFragment);
                }
                changeStatus(3);
                break;
            case 4:
                if (mUserFragment == null) {
                    mUserFragment = new User_frag();
                    transaction.add(R.id.cccc, mUserFragment);
                } else {
                    transaction.show(mUserFragment);
                }
                changeStatus(4);
                break;
            default:
                break;
        }
        // 提交事务
        transaction.commit();
    }

    /**
     * @Description:        更换不同的fragment
     * @author               miao
     * @date                 2016/4/28 11:14
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mServiceFragment != null) {
            transaction.hide(mServiceFragment);
        }
        if (mSigningFragment != null) {
            transaction.hide(mSigningFragment);
        }
        if (mChatFragment != null) {
            transaction.hide(mChatFragment);
        }
        if (mUserFragment != null) {
            transaction.hide(mUserFragment);
        }
    }

    /**
     * @Description:        改变UI
     * @author               miao
     * @date                 2016/4/28 11:15
     */
    private void changeStatus(int position) {
        // 重置所有图片
        homeImg.setImageResource(R.mipmap.home_frag);
        serviceImg.setImageResource(R.mipmap.signing_frag);
        signingImg.setImageResource(R.mipmap.signing_frag);
        chatImg.setImageResource(R.mipmap.doc_frag);
        userImg.setImageResource(R.mipmap.user_frag);
        // 重置所有文本的颜色
        homeTv.setTextColor(Color.parseColor("#999999"));
        serviceTv.setTextColor(Color.parseColor("#999999"));
        signingTv.setTextColor(Color.parseColor("#999999"));
        chatTv.setTextColor(Color.parseColor("#999999"));
        userTv.setTextColor(Color.parseColor("#999999"));
        // 改变对应图片和文本颜色
        switch (position) {
            case 0:
                homeImg.setImageResource(R.mipmap.home_frag);
                homeTv.setTextColor(Color.parseColor("#2fad68"));
                break;
            case 1:
                serviceImg.setImageResource(R.mipmap.signing_frag);
                serviceTv.setTextColor(Color.parseColor("#2fad68"));
                break;
            case 2:
                signingImg.setImageResource(R.mipmap.signing_frag);
                signingTv.setTextColor(Color.parseColor("#2fad68"));
                break;
            case 3:
                chatImg.setImageResource(R.mipmap.doc_frag);
                chatTv.setTextColor(Color.parseColor("#2fad68"));
                break;
            case 4:
                userImg.setImageResource(R.mipmap.user_frag);
                userTv.setTextColor(Color.parseColor("#2fad68"));
                break;
            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_home:
                select(0);
                break;
            case R.id.tab_service:
                select(1);
                break;
            case R.id.tab_signing:
                select(2);
                break;
            case R.id.tab_chat:
                select(3);
                break;
            case R.id.tab_user:
                select(4);
                break;
            default:
                break;
        }
    }


    long time = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - time > 2000) {
                time = System.currentTimeMillis();
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            } else {
                // finish();
                MyApplication.getInstance().ExitApp();
            }
        }
        return true;
    }
}
