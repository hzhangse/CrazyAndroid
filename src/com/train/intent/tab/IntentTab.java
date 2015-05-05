package com.train.intent.tab;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.train.R;


public class IntentTab extends TabActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		TabHost tabHost = getTabHost();
		//使用Intent添加第一个Tab页面
		tabHost.addTab(tabHost.newTabSpec("tab1")
			.setIndicator("已接电话"
				, getResources().getDrawable(R.drawable.icon))
			.setContent(new Intent(this, BeCalledActivity.class)));
		//使用Intent添加第二个Tab页面
		tabHost.addTab(tabHost.newTabSpec("tab1")
			.setIndicator("呼出电话")
			.setContent(new Intent(this, CalledActivity.class)));
		//使用Intent添加第三个Tab页面
		tabHost.addTab(tabHost.newTabSpec("tab1")
			.setIndicator("未接电话")
			.setContent(new Intent(this, NoCallActivity.class)));		
	}
}