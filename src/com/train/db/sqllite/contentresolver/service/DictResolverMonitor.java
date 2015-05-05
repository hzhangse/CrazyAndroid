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
	// ����һ��ServiceConnection����
	private ServiceConnection conn = new ServiceConnection()
	{
		// ����Activity��Service���ӳɹ�ʱ�ص��÷���
		@Override
		public void onServiceConnected(ComponentName name
			, IBinder service)
		{
			System.out.println("--Service Connected--");
			// ��ȡService��onBind���������ص�MyBinder����
			binder = (MonitorDictService.MyBinder) service;
		}
		// ����Activity��Service�Ͽ�����ʱ�ص��÷���
		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			System.out.println("--Service Disconnected--");			
		}
	};
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		final Intent intent = new Intent();
		//ΪIntent����Action����
		intent.setAction(MonitorDictService.action);
		bindService(intent , conn , Service.BIND_AUTO_CREATE);	
	}

}
