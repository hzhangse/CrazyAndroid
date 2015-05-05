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
		// �յĹ��캯��
		public MyHandler() {
		}

		// ��Looper���Ͳ������ݵĺ�����LooperΪ��Ϣ�ã�����ѭ���Ĵ���Ϣ�����еõ���Ϣ���������
		// ÿ����Ϣ���ж���һ��Looper����ΪLooper���Ѿ���װ���˵���Ϣ���к���Ϣѭ������
		public MyHandler(Looper looper) {
			// ���ø���Ĺ��캯��
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			
			// ����Ϣ�е�bundle����ȡ����
//			Bundle b = msg.getData();
//			String whether = b.getString("whether");
//			int temperature = b.getInt("temperature");
//			System.out.println("whether= " + whether + " ,temperature= "
//					+ temperature);
			
			//setTitle("" + msg.arg1);  will throws exception for not using main thread
			progress_bar.setProgress(msg.arg1);
			// ���°ѽ��̼��뵽���̶�����

			update_progress_handle.post(update_thread);
		}

	}

	private class StartOnClickListenr implements OnClickListener {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// �ý�������ʾ����
			progress_bar.setVisibility(View.VISIBLE);
			// ���̼߳��뵽handler���̶߳�����
			// �½�һ��HanderThread���󣬸ö���ʵ������Looper��������Ϣ���еĹ���
			handler_thread = new HandlerThread("handler_thread");
			handler_thread.start();
			// MyHandler�����Լ��̳е�һ���࣬�������hand_thread��Looper����ʼ����
			update_progress_handle = new MyHandler(handler_thread.getLooper());
			update_progress_handle.post(update_thread);
			
			// ���һ����Ϣmsg
			//Message msg = update_progress_handle.obtainMessage();

			// ����Bundle�������ݣ�Bundle�д�ŵ��Ǽ�ֵ�Ե�map��ֻ�����ļ�ֵ���ͺ��������ͱȽϹ̶�����
//			Bundle b = new Bundle();
//			b.putString("whether", "����");
//			b.putInt("temperature", 34);
//			msg.setData(b);
			// ��msg���͵��Լ���handler�У�����ָ����my_handler,���ø�handler��HandleMessage�����������mug
			//msg.sendToTarget();

		}
	}
}