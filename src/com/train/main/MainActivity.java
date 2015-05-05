package com.train.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.train.R;

public class MainActivity extends Activity implements OnItemClickListener {

	private ListView listview = null;

	private PackageManager pm;

	private String filterStr = "";
	private List<AppInfo> mlistAppInfo;
	private BrowseApplicationInfoAdapter browseAppAdapter = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse_app_list);
		listview = (ListView) findViewById(R.id.listviewApp);
		mlistAppInfo = new ArrayList<AppInfo>();
		if (getIntent() != null) {

			filterStr = getIntent().getStringExtra("filter");
		}
		mlistAppInfo = queryFilterAppInfo(filterStr); // 查询所有应用程序信息
		// 构建适配器，并且注册到listView
		browseAppAdapter = new BrowseApplicationInfoAdapter(this, mlistAppInfo);
		listview.setAdapter(browseAppAdapter);
		listview.setOnItemClickListener(this);
	}

	// 点击跳转至该应用程序
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long arg3) {
		// TODO Auto-generated method stub
		Intent intent = mlistAppInfo.get(position).getIntent();
		startActivity(intent);
	}

	// 根据查询条件，查询特定的ApplicationInfo
	private List<AppInfo> queryFilterAppInfo(String action) {
		pm = this.getPackageManager();

		Intent mainIntent = new Intent(action, null);

		// 通过查询，获得所有ResolveInfo对象.

		List<ResolveInfo> resolveInfos = pm.queryIntentActivities(mainIntent,
				PackageManager.GET_INTENT_FILTERS);
		// 调用系统排序 ， 根据name排序
		// 该排序很重要，否则只能显示系统应用，而不能列出第三方应用程序
		Collections.sort(resolveInfos,
				new ResolveInfo.DisplayNameComparator(pm));
		// 查询所有已经安装的应用程序
		if (mlistAppInfo != null) {
			mlistAppInfo.clear();
			for (ResolveInfo reInfo : resolveInfos) {
				String activityName = reInfo.activityInfo.name; // 获得该应用程序的启动Activity的name
				String pkgName = reInfo.activityInfo.packageName; // 获得应用程序的包名
				String appLabel = (String) reInfo.loadLabel(pm); // 获得应用程序的Label
				Drawable icon = reInfo.loadIcon(pm); // 获得应用程序图标
				// 为应用程序的启动Activity 准备Intent
				Intent launchIntent = new Intent();
				launchIntent.setComponent(new ComponentName(pkgName,
						activityName));
				// 创建一个AppInfo对象，并赋值
				AppInfo appInfo = new AppInfo();
				appInfo.setAppLabel(appLabel);
				appInfo.setPkgName(pkgName);
				appInfo.setAppIcon(icon);
				appInfo.setIntent(launchIntent);
				mlistAppInfo.add(appInfo); // 添加至列表中
			}
		}

		return mlistAppInfo;
	}

}