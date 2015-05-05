package com.train.net;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.train.R;

public class MultiThreadDown extends Activity {
	EditText url;
	EditText target;
	Button downBn;
	ProgressBar bar;
	DownUtil downUtil;
	private int mDownStatus;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mutithreaddownload);
		// 获取程序界面中的三个界面控件
		url = (EditText) findViewById(R.id.url);
		target = (EditText) findViewById(R.id.target);
		downBn = (Button) findViewById(R.id.down);
		bar = (ProgressBar) findViewById(R.id.bar);
		// 创建一个Handler对象
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x123) {
					bar.setProgress(mDownStatus);
				}
			}
		};

		final Handler  downloadhandler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					if (msg.what == 0x124) {
						downUtil = new DownUtil(url.getText().toString(), target
								.getText().toString(), 4);
						try {
							// 开始下载
							downUtil.download();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			};
			downUtil = new DownUtil(url.getText().toString(), target
					.getText().toString(), 4);
		downBn.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// 定义每秒调度获取一次系统的完成进度
				final Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						// 获取下载任务的完成比率
						double completeRate = downUtil.getCompleteRate();
						mDownStatus = (int) (completeRate * 100);
						// 发送消息通知界面更新进度条
						handler.sendEmptyMessage(0x123);
						// 下载完全后取消任务调度
						if (mDownStatus >= 100) {
							timer.cancel();
						}
					}
				}, 0, 100);
				// 初始化DownUtil对象
				Thread r = new Thread(new Runnable(){

					@Override
					public void run() {
						
							
							try {
								// 开始下载
								downUtil.download();
							} catch (Exception e) {
								e.printStackTrace();
							}
						
						//downloadhandler.sendEmptyMessage(0x124);
					}});
				r.start();
				//
			
			}
		});

	}

	public static String GetSystemVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	private static void changeThreadPolicy() {
		String strVer = GetSystemVersion();
		strVer = strVer.substring(0, 3).trim();
		float fv = Float.valueOf(strVer);
		if (fv > 2.3) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork() // 这里可以替换为detectAll()
																			// 就包括了磁盘读写和网络I/O
					.penaltyLog() // 打印logcat，当然也可以定位到dropbox，通过文件保存相应的log
					.build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectLeakedSqlLiteObjects() // 探测SQLite数据库操作
					.penaltyLog() // 打印logcat
					.penaltyDeath().build());
		}
	}
}