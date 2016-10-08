package com.zkr.peoplehomedoc.utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;

import com.zkr.peoplehomedoc.appinterface.IDownloadFileFinish;
import com.zkr.peoplehomedoc.widget.ProgressDialogStyle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * @PackageName: ---------cn.com.sinosoft.wjwapp.utils
 * @Description: ---------标准详情中的附件下载任务类
 * @author: ---------------LF
 * @date: -----------------2016/9/5 17:46
 * @Copyright: -----------中科软
 */
public class DownloadFileTask extends AsyncTask<Void,Integer,Integer> {

    private Context mContext;
    private String mUrl;
    //文件下载路径
    private String fileDir;
    //文件名称
    private String fileName;
    //下载的文件
    private File downFile;
    //下载进度
    private long mProgress = 0;
    private IDownloadFileFinish iDownloadFileFinish;
    private ProgressDialogStyle pb;
    private HttpURLConnection urlCon;

    public DownloadFileTask(Context context, String url,IDownloadFileFinish iDownloadFileFinish){
        this.mContext=context;
        this.mUrl=url;
        this.iDownloadFileFinish=iDownloadFileFinish;
    }

    @Override
    protected void onPreExecute() {
        pb=new ProgressDialogStyle(mContext);
        pb.setCancleListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消下载
                iDownloadFileFinish.cancleDownloadFile(urlCon);
            }
        });
        pb.show();
        //判断sd卡状态
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            this.fileDir= Environment.getExternalStorageDirectory()+"/jsw/download/file/";
        }else{
            ActivityUtil.toastShow((Activity) mContext,"未发现SD卡");
        }
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return  downFile();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        pb.setProgress(values[0]);
        pb.setProgressText("正在下载:"+values[0].toString()+"%");
    }

    @Override
    protected void onPostExecute(Integer result) {
        pb.dismiss();
        iDownloadFileFinish.downloadFileFinish(result);
    }

    /**
     * 下载文件
     * @return 0：下载成功   1：文件已存在     2:没有附件     -1：下载失败
     */
    private Integer downFile(){
        InputStream inputStream= null;
        try{
            URL url = new URL(mUrl);
            urlCon =(HttpURLConnection)url.openConnection();
            if(urlCon.getResponseCode()==200) {
                inputStream = urlCon.getInputStream();
                int fileLength = urlCon.getContentLength();
                //获取文件描述中的文件名
                String fileDesc=urlCon.getHeaderField("Content-Disposition");
                fileName = Uri.decode(fileDesc).split("filename=")[1].replace("\"", "");
                downFile = new File(fileDir);
                if (!downFile.exists()) {
                    downFile.mkdirs();
                }
                downFile = new File(fileDir + fileName);
                if (downFile.exists()) {
                    return 1;//文件已下载
                } else {
                    downFile.createNewFile();
                }
                //文件流写入本地文件中
                OutputStream output = new FileOutputStream(downFile);
                byte buffer[] = new byte[4 * 1024];
                int len, percent;
                while (!isCancelled() && (len = inputStream.read(buffer)) != -1) {
                    output.write(buffer, 0, len);
                    mProgress += len;
                    percent = (int) ((float) mProgress / fileLength * 100);
                    publishProgress(percent);//更新进度
                }
                output.flush();
            }else{
                return 2;
            }
        }catch(Exception e){
            e.printStackTrace();
            downFile = new File(fileDir);
            if (downFile.exists()) {
                downFile = new File(fileDir + fileName);
                if (downFile.exists()) {
                    downFile.delete();
                }
            }
            return -1;
        }finally{
            try{
                inputStream.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return 0;
    }
}
