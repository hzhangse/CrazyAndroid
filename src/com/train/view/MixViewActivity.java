package com.train.view;

import com.train.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MixViewActivity extends Activity
{
	//定义一个访问图片的数组
	int[] images = new int[]{
		R.drawable.java,
		R.drawable.ee,
		R.drawable.classic,
		R.drawable.ajax,
		R.drawable.xml,
	};
	int currentImg = 0;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.codeview);
		//获取LinearLayout布局容器
		LinearLayout main = (LinearLayout)findViewById(R.id.main);
		//程序创建ImageView组件
		final ImageView image = new ImageView(this);
		//将ImageView组件添加到LinearLayout布局容器中
		main.addView(image);
		//初始化时显示第一张图片
		image.setImageResource(images[0]);
		image.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (currentImg >= 4)
				{
					currentImg = -1;
				}
				//改变ImageView里显示的图片
				image.setImageResource(images[++currentImg]);
			}
		});
	}
}