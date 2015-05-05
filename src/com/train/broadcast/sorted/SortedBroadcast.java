package com.train.broadcast.sorted;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.train.R;

public class SortedBroadcast extends Activity
{
	Button send;
	String action = "com.train.action.sorted.broadcast";
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sortedbroadcast);
		// 获取程序中的send按钮
		send = (Button) findViewById(R.id.send);
		send.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 创建Intent对象
				Intent intent = new Intent();
				intent.setAction(action);
				intent.putExtra("msg" , "简单的消息");
				// 发送有序广播
				sendOrderedBroadcast(intent , null);
			}	
		});
	}
}