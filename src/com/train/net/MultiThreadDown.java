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
		// ��ȡ��������е���������ؼ�
		url = (EditText) findViewById(R.id.url);
		target = (EditText) findViewById(R.id.target);
		downBn = (Button) findViewById(R.id.down);
		bar = (ProgressBar) findViewById(R.id.bar);
		// ����һ��Handler����
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
							// ��ʼ����
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
				// ����ÿ����Ȼ�ȡһ��ϵͳ����ɽ���
				final Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						// ��ȡ�����������ɱ���
						double completeRate = downUtil.getCompleteRate();
						mDownStatus = (int) (completeRate * 100);
						// ������Ϣ֪ͨ������½�����
						handler.sendEmptyMessage(0x123);
						// ������ȫ��ȡ���������
						if (mDownStatus >= 100) {
							timer.cancel();
						}
					}
				}, 0, 100);
				// ��ʼ��DownUtil����
				Thread r = new Thread(new Runnable(){

					@Override
					public void run() {
						
							
							try {
								// ��ʼ����
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
					.detectDiskReads().detectDiskWrites().detectNetwork() // ��������滻ΪdetectAll()
																			// �Ͱ����˴��̶�д������I/O
					.penaltyLog() // ��ӡlogcat����ȻҲ���Զ�λ��dropbox��ͨ���ļ�������Ӧ��log
					.build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectLeakedSqlLiteObjects() // ̽��SQLite���ݿ����
					.penaltyLog() // ��ӡlogcat
					.penaltyDeath().build());
		}
	}
}