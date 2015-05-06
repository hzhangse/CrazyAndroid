package com.train.main;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

import com.train.R;

public class FilterApplication extends Activity implements OnClickListener {



	private String[] categorys = new String[] { "db", "broadcast", "event",
			"gesture", "activity", "file", "intent", "layout", "res",
			"service", "tts", "view","media" ,"net","async","sensor"};
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_list);
		// 初始化控件并监听
		LinearLayout layout = (LinearLayout) this.findViewById(R.id.root);
		Arrays.sort(categorys);
		for (String category : categorys) {
			Button button = new Button(this);
			ViewGroup.LayoutParams params = new LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			button.setLayoutParams(params);
			button.setText(category);

			button.setOnClickListener(this);
			layout.addView(button);
		}
		
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		System.out.println("" + view.getId());
		String filterStr = "com.train." + ((Button) view).getText();
		/*switch (view.getId()) {
		case R.id.btallapp:
			filter = MainActivity.FILTER_ALL_APP;
			break;
		case R.id.btsystemapp:
			filter = MainActivity.FILTER_SYSTEM_APP;
			break;
		case R.id.btthirdapp:
			filter = MainActivity.FILTER_THIRD_APP;
			break;
		case R.id.btsdcardapp:
			filter = MainActivity.FILTER_SDCARD_APP;
			break;
		}*/
		Intent intent = new Intent(getBaseContext(), MainActivity.class);
		intent.putExtra("filter", filterStr);
		startActivity(intent);
	}

}
