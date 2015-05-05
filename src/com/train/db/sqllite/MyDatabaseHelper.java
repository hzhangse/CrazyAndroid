/**
 * 
 */
package com.train.db.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDatabaseHelper extends SQLiteOpenHelper
{
	final String CREATE_TABLE_SQL =
		"create table dict(_id integer primary key autoincrement , word , detail)";
	/**
	 * @param context
	 * @param name
	 * @param version
	 */
	public MyDatabaseHelper(Context context, String name, int version)
	{
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		// ��һ��ʹ�����ݿ�ʱ�Զ�����
		db.execSQL(CREATE_TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		System.out.println("--------onUpdate Called--------" 
			+ oldVersion + "--->" + newVersion);
	}
}
