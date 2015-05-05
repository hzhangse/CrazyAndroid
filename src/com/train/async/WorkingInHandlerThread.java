package com.train.async;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class WorkingInHandlerThread extends WorkingInMainThread {
	private static final String TAG = "WorkingInHandlerThread";
	private HandlerThread handler_thread;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.e(TAG, "Main id    " + Thread.currentThread().getId() + " " + count);
		super.onCreate(savedInstanceState);
	}

	
	
	protected void setHandle() {
		start.setOnClickListener(new StartOnClickListenr());	
	}

	class MyHandler extends Handler {
		// 空的构造函数
		public MyHandler() {
		}

		// 以Looper类型参数传递的函数，Looper为消息泵，不断循环的从消息队列中得到消息并处理，因此
		// 每个消息队列都有一个Looper，因为Looper是已经封装好了的消息队列和消息循环的类
		public MyHandler(Looper looper) {
			// 调用父类的构造函数
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			
			// 将消息中的bundle数据取出来
//			Bundle b = msg.getData();
//			String whether = b.getString("whether");
//			int temperature = b.getInt("temperature");
//			System.out.println("whether= " + whether + " ,temperature= "
//					+ temperature);
			
			//setTitle("" + msg.arg1);  will throws exception for not using main thread
			progress_bar.setProgress(msg.arg1);
			// 重新把进程加入到进程队列中

			update_progress_handle.post(update_thread);
		}

	}

	private class StartOnClickListenr implements OnClickListener {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// 让进度条显示出来
			progress_bar.setVisibility(View.VISIBLE);
			// 将线程加入到handler的线程队列中
			// 新建一个HanderThread对象，该对象实现了用Looper来处理消息队列的功能
			handler_thread = new HandlerThread("handler_thread");
			handler_thread.start();
			// MyHandler类是自己继承的一个类，这里采用hand_thread的Looper来初始化它
			update_progress_handle = new MyHandler(handler_thread.getLooper());
			update_progress_handle.post(update_thread);
			
			// 获得一个消息msg
			//Message msg = update_progress_handle.obtainMessage();

			// 采用Bundle保存数据，Bundle中存放的是键值对的map，只是它的键值类型和数据类型比较固定而已
//			Bundle b = new Bundle();
//			b.putString("whether", "晴天");
//			b.putInt("temperature", 34);
//			msg.setData(b);
			// 将msg发送到自己的handler中，这里指的是my_handler,调用该handler的HandleMessage方法来处理该mug
			//msg.sendToTarget();

		}
	}
}