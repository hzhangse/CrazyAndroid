package com.train.db.sqllite.contentresolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.train.R;
import com.train.db.sqllite.ResultActivity;
import com.train.db.sqllite.contentprovider.Words;


public class DictResolver extends Activity
{
	ContentResolver contentResolver;
	protected Button insert = null;
	Button search = null;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlit_dict);
		// ��ȡϵͳ��ContentResolver����
		contentResolver = getContentResolver();
		insert = (Button)findViewById(R.id.insert);
		search = (Button)findViewById(R.id.search);	
		// Ϊinsert��ť�ĵ����¼����¼�������
		insert.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				//��ȡ�û�����
				String word = ((EditText)findViewById(R.id.word))
					.getText().toString();
				String detail = ((EditText)findViewById(R.id.detail))
					.getText().toString();
				//�������ʼ�¼
				ContentValues values = new ContentValues();
				values.put(Words.Word.WORD , word);
				values.put(Words.Word.DETAIL , detail);
				contentResolver.insert(Words.Word.DICT_CONTENT_URI , values);
				//��ʾ��ʾ��Ϣ
				Toast.makeText(DictResolver.this, "������ʳɹ���" , 8000)
					.show();
			}			
		});
		// Ϊsearch��ť�ĵ����¼����¼�������
		search.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ��ȡ�û�����
				String key = ((EditText) findViewById(R.id.key)).getText()
					.toString();
				// ִ�в�ѯ
				Cursor cursor = contentResolver.query(
					Words.Word.DICT_CONTENT_URI, null 
					, "word like ? or detail like ?"
					, new String[]{"%" + key + "%" , "%" + key + "%"} 
					, null);
				//����һ��Bundle����
				Bundle data = new Bundle();
				data.putSerializable("data", converCursorToList(cursor));
				//����һ��Intent
				Intent intent = new Intent(DictResolver.this
					, ResultActivity.class);
				intent.putExtras(data);
				//����Activity
				startActivity(intent);
			}
		});
	}

	private ArrayList<Map<String, String>> converCursorToList(
		Cursor cursor)
	{
		ArrayList<Map<String, String>> result 
			= new ArrayList<Map<String, String>>();
		// ����Cursor�����
		while (cursor.moveToNext())
		{
			// ��������е����ݴ���ArrayList��
			Map<String, String> map = new HashMap<String, String>();
			// ȡ����ѯ��¼�е�2�С���3�е�ֵ
			map.put(Words.Word.WORD, cursor.getString(1));
			map.put(Words.Word.DETAIL, cursor.getString(2));
			result.add(map);
		}
		return result;
	}
}