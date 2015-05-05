/**
 * 
 */
package com.train.db.sqllite.contentresolver.service;

import com.train.db.sqllite.contentprovider.Words;
import com.train.db.sqllite.contentresolver.observer.DictObserver;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;


public class MonitorDictService extends Service
{
	public static String action = "com.train.service.BIND_SERVICE";
	private int count;
	private boolean quit;
	// ����onBinder���������صĶ���
	private MyBinder binder = new MyBinder();
	// ͨ���̳�Binder��ʵ��IBinder��
	public class MyBinder extends Binder
	{
		public int getCount()
		{
			// ��ȡService������״̬��count
			return count;
		}
	}
	// ����ʵ�ֵķ���
	@Override
	public IBinder onBind(Intent intent)
	{
		System.out.println("Service is Binded");
		// ����IBinder����
		return binder;
	}
	// Service������ʱ�ص��÷�����
	@Override
	public void onCreate()
	{
		super.onCreate();
		System.out.println("Service is Created");
		getContentResolver().registerContentObserver(
				Uri.parse("content://" +  Words.AUTHORITY)
				, true, new DictObserver(new Handler(),this));
		// ����һ���̡߳���̬���޸�count״ֵ̬
		new Thread()
		{
			@Override
			public void run()
			{
				while (!quit)
				{
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
					}
					count++;
				}
			}
		}.start();		
	}
	// Service���Ͽ�����ʱ�ص��÷���
	@Override
	public boolean onUnbind(Intent intent)
	{
		System.out.println("Service is Unbinded");
		return true;
	}
	// Service���ر�֮ǰ�ص���
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		this.quit = true;
		System.out.println("Service is Destroyed");
	}
	
	@Override
	public void onRebind(Intent intent) 
	{
		super.onRebind(intent);
		this.quit = true;
		System.out.println("Service is ReBinded");
	}	
}
