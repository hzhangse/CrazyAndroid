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
	// ��������Activity������
	String[] names = { "���ó������", "�鿴�Ǽʱ���" };
	String[] viewNames = null;

	// ��������Activity��Ӧ��ʵ����
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
		// ���øô�����ʾ���б������Adapter
		setListAdapter(adapter);
	}

	// �����б���������ָ��Activity��Ӧ��Intent
	/*
	 * (non-Javadoc)
	 * @see android.app.LauncherActivity#intentForPosition(int)
	 */
	public Intent intentForPosition(int position) {
		return new Intent(OtherActivity.this, clazzs[position]);
	}
}