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
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// ����ȫ��
		// ���ú�����ʾ
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		// ѡ��֧�ְ�͸��ģʽ,����surfaceview��activity��ʹ�á�
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.video);
		Button choose = (Button) findViewById(R.id.btnChoose);
		choose.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(VedioViewTest.this
						, ChooseFile.class);
					//����ָ��Activity���ȴ����صĽ��������0�������룬���ڱ�ʶ������
					startActivityForResult(intent , 0);
				
			}
			
		});
		
		
	}
	@Override
	public void onActivityResult(int requestCode , int resultCode
		, Intent intent)
	{
		//��requestCode��resultCodeͬʱΪ0��Ҳ���Ǵ����ض��Ľ��
		if (requestCode == 0
			&& resultCode == 0)
		{
			//ȡ��Intent���Extras����
			Bundle data = intent.getExtras();
			//ȡ��Bundle�е�����
			String result = data.getString("choose");
			videoPath = result;
			File video = new File(videoPath);
			
			if(video.exists())
			{
				// ��ȡ������VideoView���
				videoView = (VideoView) findViewById(R.id.video);
				// ����MediaController����
				mController = new MediaController(this);
				videoView.setVideoPath(video.getAbsolutePath());
				// ����videoView��mController��������
				videoView.setMediaController(mController);
				// ����mController��videoView��������
				mController.setMediaPlayer(videoView);
				// ��VideoView��ȡ����
				videoView.requestFocus();
			}
		}
	}
}