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
		// ��ȡ��������еİ�ť
		send = (Button) findViewById(R.id.send);
		IntentFilter filter = new IntentFilter();
		// ָ��BroadcastReceiver������Action
		filter.addAction(action);
		// ע��BroadcastReceiver
		
		registerReceiver(new MyReceiver(), filter);
		send.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				
				// ����Intent����
				Intent intent = new Intent();
				// ����Intent��Action����
				intent.setAction(action);
				intent.putExtra("msg" , "�򵥵���Ϣ");
				// ���͹㲥
				sendBroadcast(intent);
			}	
		});
	}
}