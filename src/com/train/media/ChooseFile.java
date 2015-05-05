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
		
		// 为ListView的列表项的单击事件绑定监听器
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 用户单击了文件，直接返回，不做任何处理
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
					// 设置该SelectActivity的结果码，并设置结束之后退回的Activity
					ChooseFile.this.setResult(0 , intent);
					//结束SelectCityActivity。
					ChooseFile.this.finish();
					return;
				}
					
				// 获取用户点击的文件夹下的所有文件
				File[] tmp = currentFiles[position].listFiles();
				if (tmp == null || tmp.length == 0) {
					Toast.makeText(ChooseFile.this, "当前路径不可访问或该路径下没有文件", 20000)
							.show();
				} else {
					// 获取用户单击的列表项对应的文件夹，设为当前的父文件夹
					currentParent = currentFiles[position];
					// 保存当前的父文件夹内的全部文件和文件夹
					currentFiles = tmp;
					// 再次更新ListView
					inflateListView(currentFiles);
				}
			}
		});
		// 获取上一级目录的按钮
		Button parent = (Button) findViewById(R.id.parent);
		parent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				try {
					if (!currentParent.getCanonicalPath().equals("/mnt/sdcard")) {
						// 获取上一级目录
						currentParent = currentParent.getParentFile();
						// 列出当前目录下所有文件
						currentFiles = currentParent.listFiles();
						// 再次更新ListView
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
