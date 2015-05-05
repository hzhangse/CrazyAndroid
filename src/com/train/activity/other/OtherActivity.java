package com.train.activity.other;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class OtherActivity extends LauncherActivity {
	// 定义两个Activity的名称
	String[] names = { "设置程序参数", "查看星际兵种" };
	String[] viewNames = null;

	// 定义两个Activity对应的实现类
	Class<?>[] clazzs = { PreferenceActivityTest.class,
			ExpandableListActivityTest.class };

	Class<?>[] allclazzs = null;
	/*private void setinitActivity() {
		ActivityManager mActivityManager = (ActivityManager) this
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<Class> clazzs = FindClassUtils.getClasssFromPackage("com.train",
				Activity.class);
		if (clazzs.size() > 0) {
			viewNames = new String[clazzs.size()];
			allclazzs = new Class[clazzs.size()];
			int i = 0;
			for (Class cls : clazzs) {
				allclazzs[i] = cls;
				viewNames[i] = cls.getName();
				i++;
			}
		}

	}*/

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	//	setinitActivity();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, names);
		// 设置该窗口显示的列表所需的Adapter
		setListAdapter(adapter);
	}

	// 根据列表项来返回指定Activity对应的Intent
	/*
	 * (non-Javadoc)
	 * @see android.app.LauncherActivity#intentForPosition(int)
	 */
	public Intent intentForPosition(int position) {
		return new Intent(OtherActivity.this, clazzs[position]);
	}
}