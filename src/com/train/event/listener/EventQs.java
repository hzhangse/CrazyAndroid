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
		//��ȡӦ�ó����е�bn��ť
		Button bn = (Button)findViewById(R.id.bn);
		//Ϊ��ť���¼���������
		bn.setOnClickListener(new MyClickListener());
	}
	//����һ�������¼��ļ�����
	class MyClickListener implements View.OnClickListener
	{
		//ʵ�ּ����������ʵ�ֵķ������÷���������Ϊ�¼�������
		@Override
		public void onClick(View arg0)
		{
			EditText txt = (EditText)findViewById(R.id.txt);
			txt.setText("bn��ť�������ˣ�");
		}		
	}
}