/**
 * 
 */
package com.train.activity.other;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.train.R;




public class PreferenceActivityTest extends PreferenceActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// 设置显示参数设置布局。
		addPreferencesFromResource(R.xml.preferences);
	}
}
