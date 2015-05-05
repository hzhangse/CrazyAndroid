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
		//ʹ��Intent��ӵ�һ��Tabҳ��
		tabHost.addTab(tabHost.newTabSpec("tab1")
			.setIndicator("�ѽӵ绰"
				, getResources().getDrawable(R.drawable.icon))
			.setContent(new Intent(this, BeCalledActivity.class)));
		//ʹ��Intent��ӵڶ���Tabҳ��
		tabHost.addTab(tabHost.newTabSpec("tab1")
			.setIndicator("�����绰")
			.setContent(new Intent(this, CalledActivity.class)));
		//ʹ��Intent��ӵ�����Tabҳ��
		tabHost.addTab(tabHost.newTabSpec("tab1")
			.setIndicator("δ�ӵ绰")
			.setContent(new Intent(this, NoCallActivity.class)));		
	}
}