package com.train.db.sqllite.contentresolver.observer;

import android.app.Activity;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import com.train.R;

public class MonitorSms extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_monitor);
		//Ϊcontent://sms�����ݸı�ע�������
		getContentResolver().registerContentObserver(
			Uri.parse("content://sms")
			, true, new SmsObserver(new Handler()));
	}
	// �ṩ�Զ����ContentObserver��������
	private final class SmsObserver extends ContentObserver
	{
		public SmsObserver(Handler handler)
		{
			super(handler);
		}
		public void onChange(boolean selfChange)
		{
			// ��ѯ�������еĶ���(�������ڷ���״̬�Ķ��ŷ��ڷ�����)
			Cursor cursor = getContentResolver().query(
				Uri.parse("content://sms/outbox")
				, null, null, null, null);
			// ������ѯ�õ��Ľ���������ɻ�ȡ�û����ڷ��͵Ķ���
			while (cursor.moveToNext())
			{
				StringBuilder sb = new StringBuilder();
				// ��ȡ���ŵķ��͵�ַ
				sb.append("address=").append(
					cursor.getString(cursor.getColumnIndex("address")));					
				// ��ȡ���ŵı���
				sb.append(";subject=").append(
					cursor.getString(cursor.getColumnIndex("subject")));
				// ��ȡ���ŵ�����
				sb.append(";body=").append(
					cursor.getString(cursor.getColumnIndex("body")));
				// ��ȡ���ŵķ���ʱ��
				sb.append(";time=").append(
					cursor.getLong(cursor.getColumnIndex("date")));
				System.out.println("Has Sent SMS:::" + sb.toString());
			}
		}
	}	
}