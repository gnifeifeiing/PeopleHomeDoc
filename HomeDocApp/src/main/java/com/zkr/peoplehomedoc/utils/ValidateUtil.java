package com.zkr.peoplehomedoc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 控件验证
 * @author: LF
 */
public class ValidateUtil {
    /**
    * Describe:     验证是否是手机号
    * User:         LF
    * Date:         2016/3/18 11:30
    */
    public static boolean isMobileNO(String mobiles){
        Pattern p = Pattern.compile(Constants.VALIDATOR_PHONE_STR);
        Matcher m = p.matcher(mobiles);
        return m.matches();

        }

    /**
    * Describe:    验证邮箱格式
    * User:         LF
    * Date:         2016/3/18 11:31
    */
    public static boolean isEmail(CharSequence email) {
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
    * Describe:     判断账户格式是否正确（只能是字母或数字，长度在6-18之间）
    * User:         LF
    * Date:         2016/3/18 11:33
    */
    public static boolean checkAccount(String userName) {
        Pattern p = Pattern.compile("^[0-9a-zA-Z][0-9A-Za-z]{6,18}$");
        Matcher m = p.matcher(userName);
        return m.matches();
    }
    /**
    * Describe:     密码格式是否正确（只能是数字、字母、下划线）
    * User:         LF
    * Date:         2016/3/18 11:33
    */  
    public static boolean checkPassWord(String passWord) {
        Pattern p = Pattern.compile("^(?![0-9]+$)[a-zA-Z0-9_]*$");
        Matcher m = p.matcher(passWord);
        return m.matches();
    }
    public static boolean isMobile(String phoneNum) {
        if (phoneNum == null)
            return false;
        // 如果手机中有+86则会自动替换掉
        return validation("^[1][3,4,5,7,8][0-9]{9}$",
                phoneNum.replace("+86", ""));
    }
    public static boolean isPassword(String pwd) {
        /**
         * ^ 匹配一行的开头位置(?![0-9]+$) 预测该位置后面不全是数字
         * (?![a-zA-Z]+$) 预测该位置后面不全是字母
         * [0-9A-Za-z] {6,16} 由6-16位数字或这字母组成
         */
        return validation("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$", pwd);
    }
    /**
     * 正则校验
     *
     * @param
     * @param str
     *            需要校验的字符串
     * @return 验证通过返回true
     */
    public static boolean validation(String pattern, String str) {
        if (str == null)
            return false;
        return Pattern.compile(pattern).matcher(str).matches();
    }

}
