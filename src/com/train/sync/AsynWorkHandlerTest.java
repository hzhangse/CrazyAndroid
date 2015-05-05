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
					// ���̴߳��̶߳������Ƴ�
					asyncWorkHandler.getmWorkerHanler().removeCallbacks(
							update_thread);
			}
		}
	};

	private class StartOnClickListenr implements OnClickListener {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// �ý�������ʾ����
			progress_bar.setVisibility(View.VISIBLE);
			// ���̼߳��뵽handler���̶߳�����
			// �½�һ��HanderThread���󣬸ö���ʵ������Looper��������Ϣ���еĹ���
			asyncWorkHandler.getmWorkerHanler().post(update_thread);

		}
	}
}