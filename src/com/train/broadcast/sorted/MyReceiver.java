/**
 * 
 */
package com.train.broadcast.sorted;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
		// ����һ��Bundle���󣬲���������
		Bundle bundle = new Bundle();
		bundle.putString("first" , "��һ��BroadcastReceiver�������Ϣ");
		// ��bundle��������
		setResultExtras(bundle);
		// ȡ��Broadcast�ļ�������
//		abortBroadcast();
	}
}
