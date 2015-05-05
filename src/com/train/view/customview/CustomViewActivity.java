package com.train.view.customview;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

import com.train.R;


public class CustomViewActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.codeview);
		//��ȡ�����ļ��е�LinearLayout����
		LinearLayout root = (LinearLayout)findViewById(R.id.main);
		//����DrawView���
		final DrawView draw = new DrawView(this);
		//�����Զ������������ȡ��߶�
		draw.setMinimumWidth(300); 
		draw.setMinimumHeight(500); 
		//Ϊdraw�����Touch�¼�
		draw.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View arg0, MotionEvent event)
			{
				//�޸�draw�����currentX��currentY��������
				draw.currentX = event.getX();
				draw.currentY = event.getY();
				//֪ͨdraw����ػ�
				draw.invalidate();
				//����true�����������Ѿ�������¼�
				return true;
			}		
		});
		root.addView(draw);
	}
}