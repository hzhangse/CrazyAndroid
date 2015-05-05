package com.train.service.client;

import java.util.List;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.train.R;
import com.train.service.ComplexService;
import com.train.service.IPet;
import com.train.service.Person;
import com.train.service.Pet;

/**
 * Description: <br/>
 * site: <a href="http://www.crazyit.org">crazyit.org</a> <br/>
 * Copyright (C), 2001-2012, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * 
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class ComplexClient extends Activity {
	private IPet petService;
	private Button get;
	EditText personView;
	ListView showView;
	private ServiceConnection conn = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// 获取远程Service的onBind方法返回的对象的代理
			petService = IPet.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			petService = null;
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.call_complexservice);
		personView = (EditText) findViewById(R.id.person);
		showView = (ListView) findViewById(R.id.show);
		get = (Button) findViewById(R.id.get);
		// 创建所需绑定服务的Intent
		Intent intent = new Intent();
		intent.setAction(ComplexService.action);
		// 绑定远程服务
		bindService(intent, conn, Service.BIND_AUTO_CREATE);
		get.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					String personName = personView.getText().toString();
					// 调用远程Service的方法
					List<Pet> pets = petService.getPets(new Person(1,
							personName, personName));
					if (pets != null) {
						// 将程序返回的List包装成ArrayAdapter
						ArrayAdapter<Pet> adapter = new ArrayAdapter<Pet>(
								ComplexClient.this,
								android.R.layout.simple_list_item_1, pets);
						showView.setAdapter(adapter);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// 解除绑定
		this.unbindService(conn);
	}
}