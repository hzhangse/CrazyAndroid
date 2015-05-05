package com.train.res.drawable.AnimationDrawable;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.train.R;

public class AnimationDrawable extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim);
		final ImageView image = (ImageView) findViewById(R.id.image);
		
		final Animation anim = AnimationUtils.loadAnimation(this,
				R.anim.my_anim);
		
		anim.setFillAfter(true);
		Button bn = (Button) findViewById(R.id.bn);
		bn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				image.startAnimation(anim);
			}
		});

//		Button bnclip = (Button) findViewById(R.id.btnclip);
//		final ImageView imageclip = (ImageView) findViewById(R.id.imageclip);
//		final ClipDrawable drawable = (ClipDrawable) imageclip.getDrawable();
//		bnclip.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				final Handler handler = new Handler() {
//					@Override
//					public void handleMessage(Message msg) {
//
//						if (msg.what == 0x1233) {
//
//							drawable.setLevel(drawable.getLevel() + 200);
//						}
//					}
//				};
//				final Timer timer = new Timer();
//				timer.schedule(new TimerTask() {
//					@Override
//					public void run() {
//						Message msg = new Message();
//						msg.what = 0x1233;
//						
//						handler.sendMessage(msg);
//				
//						if (drawable.getLevel() >= 10000) {
//							timer.cancel();
//						}
//					}
//				}, 0, 300);
//			}
//		});

		
		
	}
}