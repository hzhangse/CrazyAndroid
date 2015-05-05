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
	// 定义onBinder方法所返回的对象
	private MyBinder binder = new MyBinder();
	// 通过继承Binder来实现IBinder类
	public class MyBinder extends Binder
	{
		public int getCount()
		{
			// 获取Service的运行状态：count
			return count;
		}
	}
	// 必须实现的方法
	@Override
	public IBinder onBind(Intent intent)
	{
		System.out.println("Service is Binded");
		// 返回IBinder对象
		return binder;
	}
	// Service被创建时回调该方法。
	@Override
	public void onCreate()
	{
		super.onCreate();
		System.out.println("Service is Created");
		getContentResolver().registerContentObserver(
				Uri.parse("content://" +  Words.AUTHORITY)
				, true, new DictObserver(new Handler(),this));
		// 启动一条线程、动态地修改count状态值
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
	// Service被断开连接时回调该方法
	@Override
	public boolean onUnbind(Intent intent)
	{
		System.out.println("Service is Unbinded");
		return true;
	}
	// Service被关闭之前回调。
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
