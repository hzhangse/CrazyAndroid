package com.train.res.drawable.ClipDrawableTest;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.train.R;


public class ClipDrawableTest extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.res_clipdrawable);
		ImageView imageview = (ImageView) findViewById(R.id.image); 
		//��ȡͼƬ����ʾ��ClipDrawable����
		final ClipDrawable drawable = (ClipDrawable) imageview.getDrawable(); 
		final Handler handler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				//�������Ϣ�Ǳ����������͵�
				if (msg.what == 0x1233)
				{
					//�޸�ClipDrawable��levelֵ
					drawable.setLevel(drawable.getLevel() + 200);
				}
			}
		};
		final Timer timer = new Timer();
		timer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				Message msg = new Message();
				msg.what = 0x1233;
				//������Ϣ��֪ͨӦ���޸�ClipDrawable�����levelֵ��
				handler.sendMessage(msg);
				//ȡ����ʱ��
				if (drawable.getLevel() >= 10000)
				{
					timer.cancel();
				}
			}
		}
		 , 0 , 300);
	}
}