package com.train.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.train.R;


public class DataAttr extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dataattr);
		Button bn = (Button)findViewById(R.id.bn);
		//Ϊbn��ť����һ��������
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//����Intent
				Intent intent = new Intent();
				String data = "http://www.crazyit.org";
				//����ָ���ַ���������Uri����
				Uri uri = Uri.parse(data);
				//ΪIntent����Action����
				intent.setAction(Intent.ACTION_VIEW);
				//����Data����
				intent.setData(uri);
				startActivity(intent);
			}
		});
		
		Button edit = (Button)findViewById(R.id.edit);
		//Ϊedit��ť����һ��������
		edit.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//����Intent
				Intent intent = new Intent();
				//ΪIntent����Action���ԣ�����Ϊ���༭��
				intent.setAction(Intent.ACTION_EDIT);
				String data = "content://com.android.contacts/contacts/1";
				//����ָ���ַ���������Uri����
				Uri uri = Uri.parse(data);				
				//����Data����
				intent.setData(uri);
				startActivity(intent);
			}
		});	
		Button call = (Button)findViewById(R.id.call);
		//Ϊedit��ť����һ��������
		call.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//����Intent
				Intent intent = new Intent();
				//ΪIntent����Action���ԣ�����Ϊ�����ţ�
				intent.setAction(Intent.ACTION_DIAL);
				String data = "tel:13800138000";
				//����ָ���ַ���������Uri����
				Uri uri = Uri.parse(data);				
				//����Data����
				intent.setData(uri);
				startActivity(intent);
			}
		});			
	}
}