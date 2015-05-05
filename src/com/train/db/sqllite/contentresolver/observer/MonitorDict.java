package com.train.db.sqllite.contentresolver.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.train.R;
import com.train.db.sqllite.contentprovider.Words;

public class MonitorDict extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_monitor);
		//为content://sms的数据改变注册监听器
		getContentResolver().registerContentObserver(
			Uri.parse("content://" +  Words.AUTHORITY)
			, true, new DictObserver(new Handler()));
	}
	// 提供自定义的ContentObserver监听器类
	
	
	
}