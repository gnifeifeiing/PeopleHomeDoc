package com.zkr.peoplehomedoc.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zkr.peoplehomedoc.R;


/**
 * @Description: -------自定义progressDialog界面
 * @author --------------LF
 * @date ----------------2016/9/5 10:25
 */
@SuppressLint("ValidFragment")
public class ProgressDialogStyle extends Dialog {

	private String progressText;
	private  int progress;
	private Context mContext;
	TextView tvProgressText;
	ProgressBar pb;
	Button btnCancle;
	View.OnClickListener onClickListener;

	public ProgressDialogStyle(Context context) {
		super(context);
		this.mContext=context;
	}

	/**
	 * 设置进度提示
	 * @param progressText	进度提示文字
     */
	public void setProgressText(String progressText) {
		tvProgressText.setText(progressText);
	}

	/**
	 * 设置进度
	 * @param progress
     */
	public void setProgress(int progress){
		pb.setProgress(progress);
	}

	/**
	 * 取消
	 * @param onClickListener
     */
	public void setCancleListener(View.OnClickListener onClickListener){
		this.onClickListener=onClickListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		init();
	}

	private void init() {
		View view = LayoutInflater.from(mContext).inflate(R.layout.download_progress_dialog_layout, null);
		tvProgressText = (TextView) view.findViewById(R.id.progress_text);// 进度文字
		pb = (ProgressBar) view.findViewById(R.id.progressBar);// 进度
		btnCancle= (Button) view.findViewById(R.id.btn_cancle);// 取消
		btnCancle.setOnClickListener(onClickListener);
		setContentView(view);

		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
		lp.width = (int) (d.widthPixels * 0.8); // 高度设置
		lp.height = (int) (d.widthPixels * 0.4);
		dialogWindow.setAttributes(lp);

		setCancelable(false);
	}
}