package com.train.res.i18n;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.train.R;


public class I18NTest extends Activity
{
	EditText etShow;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.i18n);
		etShow = (EditText) findViewById(R.id.show);
		// �����ı�������ʾ���ı�
		etShow.setText(R.string.msg);
	}
}