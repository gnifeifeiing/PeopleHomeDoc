package com.zkr.peoplehomedoc.utils;

import android.content.Context;
import android.os.Environment;

/**
 * Created by lenovo on 2016/3/17.
 */
public class Constants {
    /**
     * sharepre共享文件名
     */
    public static final String SHARE_FILES = "WJW_FILES";
    /**
     * sd卡下载的文件夹名称
     */
    public static final String SD_FILE_NAME = "zkrwjw";
    /**
     * log输出标识
     */
    public static final String TAG = "gnifeifeiing";
    /**
     * 默认每页显示的item数量
     */
    public static final int PAGE_SIZE = 10;
    /**
     * 网络请求失败监听
     */
    public static final String VOLLEY_ERROR = "请求失败";
    /**
     * 网络请求时对话框显示文字
     */
    public static final String IN_THE_REQUEST_TEXT = "请求中...";
    /**
     * 未登录提示的文字信息
     */
    public static final String NO_LOGIN_TOAST_TEXT = "您还没有登录";
    /**
     * 手机号码验证格式
     */
    public static final String VALIDATOR_PHONE_STR = "^((13[0-9])|(17[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    /**
     * 服务器地址
     * 北京：http://114.110.8.114/wjwmb/api/
     * 郑州：http://192.168.1.252:9301/wjwmb/api/
     */
    public static String SERVER_ADDRESS = "http://192.168.1.252:9301/wjwmb/api/";
    //public static String SERVER_ADDRESS = "http://114.110.8.114/wjwmb/api/";

    /**
     * webview加载超时时间设置
     */
    public static final int TIME_OUT = 15000;
    /**
     * volley超时时间设置
     */
    public static final int VOLLEY_TIME_OUT = 15000;
    /**
     * 错误日志存储路径
     *
     * @param context
     * @return
     */
    public static String getErrLogSDAddress(Context context) {
        //sd/PeoHosp/download/njztc_normal.apk
        String address = Environment.getExternalStorageDirectory() + "/" + Constants.SD_FILE_NAME + "/errlog/errlog.log";
        return address;
    }
}
