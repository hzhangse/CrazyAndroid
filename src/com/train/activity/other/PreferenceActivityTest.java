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
		// ������ʾ�������ò��֡�
		addPreferencesFromResource(R.xml.preferences);
	}
}
