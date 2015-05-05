package com.train.sync;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

import com.train.R;

public class WorkingInMainThread extends Activity {
	private static final String TAG = "WorkingInMainThread";
	protected int count = 0;
	protected Handler mHandler;
	protected ProgressBar progress_bar = null;
	protected Button start = null;

	// ����һ��handler���ڲ���ɴ�����Ϣ����
	protected Handler update_progress_handle = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			// super.handleMessage(msg);
			// ��ʾ������
			progress_bar.setProgress(msg.arg1);
			setTitle("" + msg.arg1);
			// ���°ѽ��̼��뵽���̶�����
			update_progress_handle.post(update_thread);
		}
	};// ��������ֺ������Զ���Ӵ���

	Runnable update_thread = new Runnable() {
		int i = 0;

		public void run() {
			if (i < 100) {
				// TODO Auto-generated method stub
				i += 10;
				// ���Ȼ��һ����Ϣ�ṹ
				Message msg = update_progress_handle.obtainMessage();
				// ����Ϣ�ṹ��arg1������ֵ
				msg.arg1 = i;
				// ��ʱ1s��java�е�try+catch�����Ŵ���
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				// ����Ϣ���͵���Ϣ������
				if (i <= 100)
					// ����Ϣ���͵���Ϣ������
					update_progress_handle.sendMessage(msg);
				if (i == 100)
					// ���̴߳��̶߳������Ƴ�
					update_progress_handle.removeCallbacks(update_thread);
			}
		}
	};

	private class StartOnClickListenr implements OnClickListener {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// �ý�������ʾ����
			progress_bar.setVisibility(View.VISIBLE);
			// ���̼߳��뵽handler���̶߳�����
			update_progress_handle.post(update_thread);

		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.e(TAG, "Main id    " + Thread.currentThread().getId() + " " + count);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.synctest);

		progress_bar = (ProgressBar) findViewById(R.id.progress_bar);
		start = (Button) findViewById(R.id.start);

		setHandle();
	}

	protected void setHandle() {
		start.setOnClickListener(new StartOnClickListenr());

	}

	@Override
	protected void onDestroy() {
		// ���߳��뵱ǰhandler�����

		super.onDestroy();
	}
}
