package com.train.layout;

import android.app.Activity;
import android.os.Bundle;

import com.train.R;


public class AbsoluteLayoutTest extends Activity
{
	//当第一次创建该Activity时回调该方法
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.absolutelayout);
	}
}