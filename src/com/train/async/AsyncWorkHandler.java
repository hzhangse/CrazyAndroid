package com.train.async;

import java.util.List;

import com.train.net.ws.WebServiceUtil;

import android.content.AsyncQueryHandler;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public abstract class AsyncWorkHandler extends Handler {

	private static final String TAG = "bb";

	private static Looper sLooper = null;

	private static final int EVENT_ARG_WORK = 1;

	private WorkerHandler mWorkerHanler;

	protected final class WorkerArgs {
		Handler handler;
		Object inputData;
	}

	public AsyncWorkHandler() {
		synchronized (AsyncQueryHandler.class) {
			if (sLooper == null) {
				HandlerThread thread = new HandlerThread("AsyncWorkHandler");
				thread.start();
				sLooper = thread.getLooper();
			}
		}
		mWorkerHanler = new WorkerHandler(sLooper);
	}

	public WorkerHandler getmWorkerHanler() {
		return mWorkerHanler;
	}

	protected class WorkerHandler extends Handler {
		public WorkerHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			if (msg.obj instanceof WorkerArgs) {
				WorkerArgs args = (WorkerArgs) msg.obj;
				Message result = args.handler.obtainMessage();
				result.arg1 = EVENT_ARG_WORK;
				result.obj = args.inputData;
				result.sendToTarget();
			}

		}

	}

	/**
	 * 需要重写的回调函数
	 */
	protected void onCompleteWork(Object obj) {

	}

	public void doWork(Object inputData) {
		if (inputData != null) {
			Message msg = mWorkerHanler.obtainMessage();
			WorkerArgs workArgs = new WorkerArgs();
			workArgs.handler = this;
		
			workArgs.inputData = inputData;
			msg.obj = workArgs;
			mWorkerHanler.sendMessage(msg);
		}
	}

	protected abstract Object excuteTask() ;
	
	class TaskRunnalbe implements Runnable{
		public void run() {
			doWork(excuteTask());
		}
	};
	
	public Runnable execute() {	
		Runnable r = new TaskRunnalbe();
		mWorkerHanler.post(r);
		return r;
	}
	
	public void removeWorker(Runnable r) {		
		mWorkerHanler.removeCallbacks(r);
	}

	@Override
	public void handleMessage(Message msg) {

		Log.i(TAG, "main handler ----------------" + msg.arg1);

		if (EVENT_ARG_WORK == msg.arg1) {
			onCompleteWork(msg.obj);
		}
	}

}