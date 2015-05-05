/**
 * 
 */
package com.train.broadcast.system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class LaunchReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		Intent tIntent = new Intent(context , LaunchService.class);
		// Æô¶¯Ö¸¶¨Service
		context.startService(tIntent);
	}
}