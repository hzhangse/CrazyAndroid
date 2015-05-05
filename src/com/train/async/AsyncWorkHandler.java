package com.train.async;

import android.content.AsyncQueryHandler;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class AsyncWorkHandler extends Handler{
	
	private static final String TAG = "bb";
	
	private static Looper sLooper = null;
	
	private static final int EVENT_ARG_WORK = 1;
	
	private WorkerHandler mWorkerHanler ;
	
	protected final class WorkerArgs{
		Handler handler;
	}
	
	public AsyncWorkHandler(){
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
        	
        	WorkerArgs args = (WorkerArgs) msg.obj;
        	
        	int info = msg.arg1;
        	
        	Log.i(TAG, "worker handler=-------------------"+info);
            
        	Message result = args.handler.obtainMessage();
        	
        	result.arg1 = EVENT_ARG_WORK;
        	result.obj = info;
        	result.sendToTarget();
        }
        
	}
	
	/**
	 * 需要重写的回调函数
	 */
	protected void onCompleteWork(Object data){
		
	}
	
	public void doWork(int strInfo){
		
		Message msg = mWorkerHanler.obtainMessage();
		
		WorkerArgs workArgs = new WorkerArgs();
		workArgs.handler = this;
		msg.obj = workArgs;
		msg.arg1 = strInfo;
		mWorkerHanler.sendMessage(msg);
	}
	
	public void doWork(int strInfo,int b){
		
		Message msg = mWorkerHanler.obtainMessage();
		
		WorkerArgs workArgs = new WorkerArgs();
		workArgs.handler = this;
		msg.obj = workArgs;
		msg.arg1 = strInfo;
		mWorkerHanler.sendMessage(msg);
	}
	
	@Override
	public void handleMessage(Message msg) {
		
		Log.i(TAG, "main handler ----------------"+msg.arg1);
		
		if(EVENT_ARG_WORK == msg.arg1){
			onCompleteWork(msg.obj);
		}
	}
	
	
}