package com.zkr.peoplehomedoc.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Describe:     sharepreference属性存储
 * User:         LF
 * Date:         2016/3/18 16:32
 */
public class OptsharepreInterface {

    private SharedPreferences  settings; // static

    public OptsharepreInterface(Context context) {
        // 载入配置文件
        settings = context.getSharedPreferences(Constants.SHARE_FILES,
                Context.MODE_PRIVATE);
    }

    public SharedPreferences.Editor getEditor() {

        return settings.edit();
    }


    public void putPres(String optName, String values) {
        SharedPreferences.Editor editor = settings.edit();
        if (optName.equals("userId")) {
            editor.putString("userId", values);// 登录人id
        }
        else if (optName.equals("tokenApi")) {
            editor.putString("tokenApi", values);//登录成功后返回的api令牌信息
        }
        else if (optName.equals("tokenCas")) {
            editor.putString("tokenCas", values);//登录成功后返回的cas令牌信息
        }
        else if (optName.equals("loginName")) {
            editor.putString("loginName", values);// 登录账号
        }
        else if (optName.equals("password")) {
            editor.putString("password", values);// 登录密码
        }
        else if (optName.equals("loginFlag")) {
            editor.putString("loginFlag", values);// 登录状态
        }
        else if (optName.equals("name")) {
            editor.putString("name", values);// 名字
        }
        else if (optName.equals("roles")) {
            editor.putString("roles", values);// 角色信息
        }
        else if (optName.equals("grants ")) {
            editor.putString("grants", values);// 授权信息
        }
        else if (optName.equals("headImg")) {
            editor.putString("headImg", values);// 头像保存路径
        }
        else if (optName.equals("phone")) {
            editor.putString("phone", values);// 手机号
        }
        else if (optName.equals("isFirstLogin")) {
            editor.putString("isFirstLogin", values);// 是否首次登陆(0:是	1:否)
        }
        else if (optName.equals("roleId")) {
            editor.putString("roleId", values);// 角色id
        }
        else {
            editor.putString(optName, values);
        }
        editor.commit();
    }

    public String getPres(String optName) {
        String values = "";
        if (optName.equals("userId")) {// 获取登陆人guid
            values = settings.getString("userId", "");
        }
        else if (optName.equals("tokenApi")) {//登录成功后返回的api令牌信息
            values = settings.getString("tokenApi", "");
        }
        else if (optName.equals("tokenCas")) {//登录成功后返回的cas令牌信息
            values = settings.getString("tokenCas", "");
        }
        else if (optName.equals("loginName")) {// 登录账号
            values = settings.getString("loginName", "");
        }
        else if (optName.equals("password")) {// 登录密码
            values = settings.getString("password", "");
        }
        else if (optName.equals("loginFlag")) {// 登录状态
            values = settings.getString("loginFlag", "false");
        }
        else if (optName.equals("name")) {//名字 roles
            values = settings.getString("name", "");
        }
        else if (optName.equals("roles")) {//角色信息
            values = settings.getString("roles", "");
        }
        else if (optName.equals("grants")) {//授权信息
            values = settings.getString("grants", "");
        }
        else if (optName.equals("headImg")) {//头像保存路径
            values = settings.getString("headImg", "");
        }
        else if (optName.equals("phone")) {// 手机号
            values = settings.getString("phone", "");
        }
        else if (optName.equals("isFirstLogin")) {// 是否首次登陆
            values = settings.getString("isFirstLogin", "0");
        }
        else if (optName.equals("roleId")) {// 角色id
            values = settings.getString("roleId", "");
        }
        else {
            values = settings.getString(optName, "");
        }
        return values;
    }

    /**
     * @Description: -------是否存在该字段
     * @author --------------LF
     * @date ----------------2016/8/19 15:09
     */
    public boolean existResult(String result) {
        return settings.contains(result);
    }
    /**
     * @Description: -------移除该字段
     * @author --------------LF
     * @date ----------------2016/8/19 15:09
     */
    public void removePre(String preName) {
        settings.edit().remove(preName).commit();
    }
    /**
     * @Description: -------清除用户信息--退出app
     * @author --------------LF
     * @date ----------------2016/8/25 11:23
     */
    public void clearUserInfo(){
        SharedPreferences.Editor editor=settings.edit();
        editor.putString("loginFlag","false");
        editor.remove("userId");// 登录人id
        editor.remove("tokenApi");//登录成功后返回的api令牌信息
        editor.remove("tokenCas");//登录成功后返回的cas令牌信息
        editor.remove("loginName");// 登录账号
        editor.remove("password");// 登录密码
        editor.remove("name");// 名字
        editor.remove("roles");// 角色信息
        editor.remove("grants");// 授权信息
        editor.remove("headImg");// 清除用户头像信息
        editor.commit();
    }

}
