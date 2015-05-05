package com.train.media;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.train.R;
import com.train.activity.result.ActivityForResult;
import com.train.activity.result.SelectCityActivity;


public class VedioViewTest extends Activity
{
	VideoView videoView;
	MediaController mController;
	String videoPath;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
		// 设置横屏显示
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		// 选择支持半透明模式,在有surfaceview的activity中使用。
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.video);
		Button choose = (Button) findViewById(R.id.btnChoose);
		choose.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(VedioViewTest.this
						, ChooseFile.class);
					//启动指定Activity并等待返回的结果，其中0是请求码，用于标识该请求
					startActivityForResult(intent , 0);
				
			}
			
		});
		
		
	}
	@Override
	public void onActivityResult(int requestCode , int resultCode
		, Intent intent)
	{
		//当requestCode、resultCode同时为0，也就是处理特定的结果
		if (requestCode == 0
			&& resultCode == 0)
		{
			//取出Intent里的Extras数据
			Bundle data = intent.getExtras();
			//取出Bundle中的数据
			String result = data.getString("choose");
			videoPath = result;
			File video = new File(videoPath);
			
			if(video.exists())
			{
				// 获取界面上VideoView组件
				videoView = (VideoView) findViewById(R.id.video);
				// 创建MediaController对象
				mController = new MediaController(this);
				videoView.setVideoPath(video.getAbsolutePath());
				// 设置videoView与mController建立关联
				videoView.setMediaController(mController);
				// 设置mController与videoView建立关联
				mController.setMediaPlayer(videoView);
				// 让VideoView获取焦点
				videoView.requestFocus();
			}
		}
	}
}