/**
 * 
 */
package com.train.db.sqllite;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.train.R;

public class ResultActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlit_dict_popup);
		ListView listView = (ListView)findViewById(R.id.show);
		Intent intent = getIntent();
		//��ȡ��intent��Я��������
		Bundle data = intent.getExtras();
		//��Bundle���ݰ���ȡ������
		@SuppressWarnings("unchecked")
		List<Map<String , String>> list = 
			(List<Map<String , String>>)data.getSerializable("data");
		//��List��װ��SimpleAdapter
		SimpleAdapter adapter = new SimpleAdapter(
			ResultActivity.this , list
			, R.layout.sqlit_dict_line , new String[]{"word" , "detail"}
			, new int[]{R.id.word , R.id.detail});
		//���ListView
		listView.setAdapter(adapter);
	}
}
