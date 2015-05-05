/**
 * 
 */
package com.train.db.sqllite.contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Words
{
	// �����ContentProvider��Authority
	public static final String AUTHORITY 
		= "com.train.providers.dictprovider";
	public static final String TYPE = "org.train.dict";
	
	//����һ����̬�ڲ���
	public static final class Word implements BaseColumns
	{
		// ����Content�����������3��������
		public final static String _ID = "_id";
		public final static String WORD = "word";
		public final static String DETAIL = "detail";
		// �����Content�ṩ���������Uri
		public final static Uri DICT_CONTENT_URI = 
			Uri.parse("content://" +  AUTHORITY + "/words");
		public final static Uri WORD_CONTENT_URI = 
			Uri.parse("content://" +  AUTHORITY + "/word");		
	}
}
