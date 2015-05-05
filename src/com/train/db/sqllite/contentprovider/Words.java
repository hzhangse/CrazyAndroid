/**
 * 
 */
package com.train.db.sqllite.contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Words
{
	// 定义该ContentProvider的Authority
	public static final String AUTHORITY 
		= "com.train.providers.dictprovider";
	public static final String TYPE = "org.train.dict";
	
	//定义一个静态内部类
	public static final class Word implements BaseColumns
	{
		// 定义Content所允许操作的3个数据列
		public final static String _ID = "_id";
		public final static String WORD = "word";
		public final static String DETAIL = "detail";
		// 定义该Content提供服务的两个Uri
		public final static Uri DICT_CONTENT_URI = 
			Uri.parse("content://" +  AUTHORITY + "/words");
		public final static Uri WORD_CONTENT_URI = 
			Uri.parse("content://" +  AUTHORITY + "/word");		
	}
}
