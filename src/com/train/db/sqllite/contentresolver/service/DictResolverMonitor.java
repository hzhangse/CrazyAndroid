package com.train.db.sqllite.contentresolver.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.train.R;
import com.train.db.sqllite.contentprovider.Words;
import com.train.db.sqllite.contentresolver.DictResolver;

public class DictResolverMonitor extends DictResolver {
	MonitorDictService.MyBinder binder;
	// 定义一个ServiceConnection对象
	private ServiceConnection conn = new ServiceConnection()
	{
		// 当该Activity与Service连接成功时回调该方法
		@Override
		public void onServiceConnected(ComponentName name
			, IBinder service)
		{
			System.out.println("--Service Connected--");
			// 获取Service的onBind方法所返回的MyBinder对象
			binder = (MonitorDictService.MyBinder) service;
		}
		// 当该Activity与Service断开连接时回调该方法
		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			System.out.println("--Service Disconnected--");			
		}
	};
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		final Intent intent = new Intent();
		//为Intent设置Action属性
		intent.setAction(MonitorDictService.action);
		bindService(intent , conn , Service.BIND_AUTO_CREATE);	
	}

}
