/**
 * 
 */
package com.train.broadcast.system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


public class BatteryReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		Bundle bundle = intent.getExtras();
		// ��ȡ��ǰ����
		int current = bundle.getInt("level");
		// ��ȡ�ܵ���
		int total = bundle.getInt("scale");
		// �����ǰ����С���ܵ�����15%
		if (current * 1.0 / total < 0.15)
		{
			Toast.makeText(context , "�������ͣ��뾡���磡" 
				, 5000)
				.show();			
		}
	}
}