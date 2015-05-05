package com.train.sync;

import android.view.View;
import android.view.View.OnClickListener;

public class AsynWorkHandlerTest extends WorkingInMainThread {

	AsyncWorkHandler asyncWorkHandler = new AsyncWorkHandler() {
		@Override
		protected void onCompleteWork(Object obj) {
			Integer status = (Integer)obj;
			setTitle(status.toString()); 
			progress_bar.setProgress(status.intValue());
			asyncWorkHandler.getmWorkerHanler().post(update_thread);
		}
	};

	protected void setHandle() {
		start.setOnClickListener(new StartOnClickListenr());
	}

	Runnable update_thread = new Runnable() {
		int i = 0;

		public void run() {
			if (i < 100) {
				i += 10;

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				asyncWorkHandler.doWork(i);
				if (i == 100)
					// 把线程从线程队列中移除
					asyncWorkHandler.getmWorkerHanler().removeCallbacks(
							update_thread);
			}
		}
	};

	private class StartOnClickListenr implements OnClickListener {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// 让进度条显示出来
			progress_bar.setVisibility(View.VISIBLE);
			// 将线程加入到handler的线程队列中
			// 新建一个HanderThread对象，该对象实现了用Looper来处理消息队列的功能
			asyncWorkHandler.getmWorkerHanler().post(update_thread);

		}
	}
}