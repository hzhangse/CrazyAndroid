package com.train.broadcast.system;


import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class LaunchService extends Service
{
	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
	@Override
	public void onCreate()
	{
		// ����1��ִ��һ�����
		new Timer().schedule(new TimerTask()
		{

			@Override
			public void run()
			{
				System.out.println("-----"
					+ new Date() + "-----");	
			}
		}, 0, 1000);
	}
}