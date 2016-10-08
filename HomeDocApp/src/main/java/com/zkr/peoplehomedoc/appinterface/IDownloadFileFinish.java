package com.zkr.peoplehomedoc.appinterface;

import java.net.HttpURLConnection;

/**
 * @PackageName: ---------cn.com.sinosoft.wjwapp.appinterface
 * @Description: ---------文件下载完成接口
 * @author: ---------------LF
 * @date: -----------------2016/9/5 18:30
 * @Copyright: -----------中科软
 */
public interface IDownloadFileFinish {
    void downloadFileFinish(int result);
    void cancleDownloadFile(HttpURLConnection urlCon);
}
