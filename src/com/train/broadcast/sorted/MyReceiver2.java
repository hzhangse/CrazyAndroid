/**
 * 
 */
package com.train.broadcast.sorted;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		Bundle bundle = getResultExtras(true);
		// ����ǰһ��BroadcastReceiver�������keyΪfirst����Ϣ
		String first = bundle.getString("first");
		Toast.makeText(context , "��һ��Broadcast�������ϢΪ��"
			+ first	, 5000)
			.show();		
	}
}
