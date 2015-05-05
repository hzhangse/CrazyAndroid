package com.train.db.sqllite.contentresolver.observer;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;

import com.train.db.sqllite.contentprovider.Words;

public class DictObserver extends ContentObserver {
	private Context ctx;
	public DictObserver(Handler handler) {
		super(handler);
	}
	
	public DictObserver(Handler handler,Context ctx) {
		super(handler);
		this.ctx = ctx;
	}


	public void onChange(boolean selfChange) {
		// 查询发送箱中的短信(处于正在发送状态的短信放在发送箱)
		Cursor cursor = ctx.getContentResolver().query(
				Words.Word.DICT_CONTENT_URI, null, null, null, null);
		while (cursor.moveToNext()) {
			String msg = cursor.getString(1) + cursor.getString(2);
			System.out.println(msg);
			Log.e("com.train", msg);
		}
	}
}
