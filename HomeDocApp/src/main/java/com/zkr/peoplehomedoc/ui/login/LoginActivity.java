package com.zkr.peoplehomedoc.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.zkr.peoplehomedoc.MainActivity;
import com.zkr.peoplehomedoc.MyApplication;
import com.zkr.peoplehomedoc.R;
import com.zkr.peoplehomedoc.base.BaseActivity;
import com.zkr.peoplehomedoc.utils.ActivityUtil;
import com.zkr.peoplehomedoc.utils.JsonUtils;
import com.zkr.peoplehomedoc.utils.MD5Util;
import com.zkr.peoplehomedoc.utils.OptsharepreInterface;
import com.zkr.peoplehomedoc.utils.ParseJsonUtils;
import com.zkr.peoplehomedoc.widget.IStringRequest;
import com.zkr.peoplehomedoc.widget.TitleBarUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Description: -------登录
 * @author --------------LF
 * @date ----------------2016/8/11 16:15
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.titleBar)
    TitleBarUtils titleBar;
    @Bind(R.id.et_user_account)
    EditText etUserAccount;
    @Bind(R.id.et_user_pwd)
    EditText etUserPwd;
    @Bind(R.id.btn_register)
    TextView btnRegister;
    @Bind(R.id.btn_submit)
    Button btnSubmit;

    private String account, pwd;
    private Map<String,Object> userMap=new HashMap<>();
    private List<Map<String,Object>> roles=new ArrayList<>();
    private OptsharepreInterface sharePre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sharePre=new OptsharepreInterface(this);
        init();
    }

    private void init() {
        initTitle();
        initWidget();
    }

    private void initTitle() {
        titleBar.setTitle("登录");
        titleBar.setLeftButtonClick(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initWidget() {
        btnSubmit.getBackground().setAlpha(100);//0~255透明度值
        btnSubmit.setEnabled(false);
        btnSubmit.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        etUserAccount.addTextChangedListener(new TextChange());
        etUserPwd.addTextChangedListener(new TextChange());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                validation();
                break;
            case R.id.btn_register:
                break;
            default:
                break;
        }
    }

    /**
     * @Description: -------判断登录账号格式
     * @author --------------LF
     * @date ----------------2016/8/11 16:21
     */
    private void validation() {
        account = etUserAccount.getText().toString().trim();
        pwd = etUserPwd.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            ActivityUtil.toastShow(this,"请输入手机号");
        } else {
            if (TextUtils.isEmpty(account)) {
                ActivityUtil.toastShow(this,"请输入密码");
            } else {
                loginMethod();
            }
        }
    }
    /**
     * @Description: -------请求登录
     * @author --------------LF
     * @date ----------------2016/8/19 11:11
     */
    private void loginMethod() {
        IStringRequest request = new IStringRequest(this, Request.Method.POST, "login", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String result = ParseJsonUtils.getParseData(LoginActivity.this, response);
                if (result != null) {
                    try {
                        userMap= JsonUtils.getMapObj(result);
                        roles=JsonUtils.getListMap(userMap.get("roles").toString());
                        saveUserInfo();
                        ActivityUtil.switchTo(LoginActivity.this,MainActivity.class,false);
                        ActivityUtil.toastShow(LoginActivity.this,"登陆成功");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();
                map.put("userName", account);
                map.put("password", MD5Util.encodeMD5ToCapital(pwd));
                return map;
            }
        };
        MyApplication.getSingleQueue().add(request);
    }
    /**
     * @Description: -------储存用户登录信息
     * @author --------------LF
     * @date ----------------2016/8/25 10:19
     */
    private void saveUserInfo() {
        MyApplication.loginFlag=true;
        sharePre.putPres("loginFlag","true");//改变登陆状态
        sharePre.putPres("userId", userMap.get("userId").toString());// 登录人id
        sharePre.putPres("tokenApi",  userMap.get("tokenApi").toString());//登录成功后返回的api令牌信息
        sharePre.putPres("tokenCas",  userMap.get("tokenCas").toString());//登录成功后返回的cas令牌信息
        sharePre.putPres("loginName",  userMap.get("loginName").toString());// 登录账号
        sharePre.putPres("password",  MD5Util.encodeMD5ToCapital(pwd));// 登录密码
        sharePre.putPres("name",  userMap.get("name").toString());// 名字
        sharePre.putPres("roles",  userMap.get("roles").toString());// 角色信息
        sharePre.putPres("grants",  userMap.get("grants").toString());// 授权信息
        sharePre.putPres("roleId",roles.get(0).get("roleId").toString());//角色id
    }

    /**
     * @Description: -------根据账号和密码输入长度判断登陆按钮是否能用
     * @author --------------LF
     * @date ----------------2016/8/19 11:12
     */
    class TextChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (etUserAccount.getText().toString().length() <= 0 || etUserPwd.getText().toString().length() <= 5) {
                btnSubmit.getBackground().setAlpha(100);//0~255透明度值
                btnSubmit.setEnabled(false);

            } else {
                btnSubmit.getBackground().setAlpha(255);
                btnSubmit.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
