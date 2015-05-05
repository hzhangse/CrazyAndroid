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

	// 创建一个handler，内部完成处理消息方法
	protected Handler update_progress_handle = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			// super.handleMessage(msg);
			// 显示进度条
			progress_bar.setProgress(msg.arg1);
			setTitle("" + msg.arg1);
			// 重新把进程加入到进程队列中
			update_progress_handle.post(update_thread);
		}
	};// 不加这个分号则不能自动添加代码

	Runnable update_thread = new Runnable() {
		int i = 0;

		public void run() {
			if (i < 100) {
				// TODO Auto-generated method stub
				i += 10;
				// 首先获得一个消息结构
				Message msg = update_progress_handle.obtainMessage();
				// 给消息结构的arg1参数赋值
				msg.arg1 = i;
				// 延时1s，java中的try+catch用来排错处理
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				// 把消息发送到消息队列中
				if (i <= 100)
					// 把消息发送到消息队列中
					update_progress_handle.sendMessage(msg);
				if (i == 100)
					// 把线程从线程队列中移除
					update_progress_handle.removeCallbacks(update_thread);
			}
		}
	};

	private class StartOnClickListenr implements OnClickListener {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// 让进度条显示出来
			progress_bar.setVisibility(View.VISIBLE);
			// 将线程加入到handler的线程队列中
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
		// 将线程与当前handler解除绑定

		super.onDestroy();
	}
}
