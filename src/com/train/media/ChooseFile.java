package com.train.media;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.train.R;
import com.train.activity.result.SelectCityActivity;
import com.train.file.sdcard.SDFileExplorer;

public class ChooseFile extends SDFileExplorer {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// ΪListView���б���ĵ����¼��󶨼�����
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// �û��������ļ���ֱ�ӷ��أ������κδ���
				if (currentFiles[position].isFile()&&currentFiles[position].getName().lastIndexOf(".3gp")>0){
					Intent intent = getIntent();
					Bundle data = new Bundle();
					
					try {
						data.putString("choose" ,currentFiles[position].getCanonicalPath());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					intent.putExtras(data);
					// ���ø�SelectActivity�Ľ���룬�����ý���֮���˻ص�Activity
					ChooseFile.this.setResult(0 , intent);
					//����SelectCityActivity��
					ChooseFile.this.finish();
					return;
				}
					
				// ��ȡ�û�������ļ����µ������ļ�
				File[] tmp = currentFiles[position].listFiles();
				if (tmp == null || tmp.length == 0) {
					Toast.makeText(ChooseFile.this, "��ǰ·�����ɷ��ʻ��·����û���ļ�", 20000)
							.show();
				} else {
					// ��ȡ�û��������б����Ӧ���ļ��У���Ϊ��ǰ�ĸ��ļ���
					currentParent = currentFiles[position];
					// ���浱ǰ�ĸ��ļ����ڵ�ȫ���ļ����ļ���
					currentFiles = tmp;
					// �ٴθ���ListView
					inflateListView(currentFiles);
				}
			}
		});
		// ��ȡ��һ��Ŀ¼�İ�ť
		Button parent = (Button) findViewById(R.id.parent);
		parent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				try {
					if (!currentParent.getCanonicalPath().equals("/mnt/sdcard")) {
						// ��ȡ��һ��Ŀ¼
						currentParent = currentParent.getParentFile();
						// �г���ǰĿ¼�������ļ�
						currentFiles = currentParent.listFiles();
						// �ٴθ���ListView
						inflateListView(currentFiles);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	protected void inflateListView(File[] files) {
		super.inflateListView(files);
		
	}
}
