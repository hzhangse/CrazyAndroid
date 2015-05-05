/**
 * 
 */
package com.train.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class MyReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		Toast.makeText(context , "���յ���Intent��ActionΪ��"
			+ intent.getAction() 
			+ "\n��Ϣ�����ǣ�" + intent.getStringExtra("msg")
			, 5000)
			.show();
	}
}
