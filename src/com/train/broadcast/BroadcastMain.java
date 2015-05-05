package com.train.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.train.R;


public class BroadcastMain extends Activity
{
	Button send;
	String action = "com.train.action.CRAZY_BROADCAST";
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendbroadcast);
		// 获取程序界面中的按钮
		send = (Button) findViewById(R.id.send);
		IntentFilter filter = new IntentFilter();
		// 指定BroadcastReceiver监听的Action
		filter.addAction(action);
		// 注册BroadcastReceiver
		
		registerReceiver(new MyReceiver(), filter);
		send.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				
				// 创建Intent对象
				Intent intent = new Intent();
				// 设置Intent的Action属性
				intent.setAction(action);
				intent.putExtra("msg" , "简单的消息");
				// 发送广播
				sendBroadcast(intent);
			}	
		});
	}
}