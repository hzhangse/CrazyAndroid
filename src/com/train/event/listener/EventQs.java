package com.train.event.listener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.train.R;


public class EventQs extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eventqs);
		//获取应用程序中的bn按钮
		Button bn = (Button)findViewById(R.id.bn);
		//为按钮绑定事件监听器。
		bn.setOnClickListener(new MyClickListener());
	}
	//定义一个单击事件的监听器
	class MyClickListener implements View.OnClickListener
	{
		//实现监听器类必须实现的方法，该方法将会作为事件处理器
		@Override
		public void onClick(View arg0)
		{
			EditText txt = (EditText)findViewById(R.id.txt);
			txt.setText("bn按钮被单击了！");
		}		
	}
}