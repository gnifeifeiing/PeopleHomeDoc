package com.zkr.peoplehomedoc.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.zkr.peoplehomedoc.MyApplication;
import com.zkr.peoplehomedoc.ui.login.LoginActivity;

import java.util.Map;


/**
 * @PackageName: ---------cn.com.sinosoft.wjwapp.utils
 * @Description: ---------Activity辅助工具
 * @author: ---------------LF
 * @date: -----------------2016/8/12 17:17
 * @Copyright: -----------中科软
 */
public class ActivityUtil {
    /**
     * description :设置Activity全屏显示。
     *
     * @param activity Activity引用
     * @param isFull   true为全屏，false为非全屏
     */
    public static void setFullScreen(Activity activity, boolean isFull) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        if (isFull) {
            params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            window.setAttributes(params);
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else {
            params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.setAttributes(params);
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    /**
     * description :跳转到某个Activity
     *
     * @param activity       本Activity
     * @param targetActivity 目标Activity的Class
     * @param isLogin        是否需要登录判断
     */
    public static void switchTo(Activity activity, Class<? extends Activity> targetActivity, boolean isLogin) {
        if (isLogin) {
            if (MyApplication.loginFlag) {
                switchTo(activity, new Intent(activity, targetActivity));
            } else {
                toastShow(activity,Constants.NO_LOGIN_TOAST_TEXT);
                switchTo(activity, new Intent(activity, LoginActivity.class));
            }
        } else {
            switchTo(activity, new Intent(activity, targetActivity));
        }
    }

    /**
     * description :根据给定的Intent进行Activity跳转
     *
     * @param activity Activity对象
     * @param intent   要传递的Intent对象
     */
    public static void switchTo(Activity activity, Intent intent) {
        activity.startActivity(intent);
    }

    /**
     * description :带参数进行Activity跳转
     *
     * @param activity       Activity对象
     * @param targetActivity 目标Activity的Class
     * @param params         跳转所带的参数
     * @param isLogin        是否需要登录判断
     */
    public static void switchTo(Activity activity, Class<? extends Activity> targetActivity, Map<String, Object> params,boolean isLogin) {
        if (isLogin) {
            if (MyApplication.loginFlag) {
                if (null != params) {
                    Intent intent = new Intent(activity, targetActivity);
                    for (Map.Entry<String, Object> entry : params.entrySet()) {
                        setValueToIntent(intent, entry.getKey(), entry.getValue());
                    }
                    switchTo(activity, intent);
                }
            } else {
                toastShow(activity,Constants.NO_LOGIN_TOAST_TEXT);
                switchTo(activity, new Intent(activity, LoginActivity.class));
            }
        } else {
            if (null != params) {
                Intent intent = new Intent(activity, targetActivity);
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    setValueToIntent(intent, entry.getKey(), entry.getValue());
                }
                switchTo(activity, intent);
            }
        }
    }

    /**
     * description :将值设置到Intent里
     *
     * @param intent Inent对象
     * @param key    Key
     * @param val    Value
     */
    public static void setValueToIntent(Intent intent, String key, Object val) {
        if (val instanceof Boolean)
            intent.putExtra(key, (Boolean) val);
        else if (val instanceof Boolean[])
            intent.putExtra(key, (Boolean[]) val);
        else if (val instanceof String)
            intent.putExtra(key, (String) val);
        else if (val instanceof String[])
            intent.putExtra(key, (String[]) val);
        else if (val instanceof Integer)
            intent.putExtra(key, (Integer) val);
        else if (val instanceof Integer[])
            intent.putExtra(key, (Integer[]) val);
        else if (val instanceof Long)
            intent.putExtra(key, (Long) val);
        else if (val instanceof Long[])
            intent.putExtra(key, (Long[]) val);
        else if (val instanceof Double)
            intent.putExtra(key, (Double) val);
        else if (val instanceof Double[])
            intent.putExtra(key, (Double[]) val);
        else if (val instanceof Float)
            intent.putExtra(key, (Float) val);
        else if (val instanceof Float[])
            intent.putExtra(key, (Float[]) val);
    }

    /**
     * description :显示Toast消息，并保证运行在UI线程中
     * @param activity
     * @param message
     */
    public static void toastShow(final Activity activity, final String message) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
