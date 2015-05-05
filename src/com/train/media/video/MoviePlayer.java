package com.train.media.video;

import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.SurfaceView;

public class MoviePlayer {
	private MediaPlayer mPlayer;


	public MoviePlayer() {
		super();
	}

	public void play(String fileName, SurfaceView view) {
		mPlayer = new MediaPlayer();
		mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mPlayer.setDisplay(view.getHolder()); // å®šä¹‰ä¸?¸ªSurfaceViewæ’­æ”¾å®?

		mPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer arg0) {
				stop();

				// canvas.drawColor(Color.TRANSPARENT,
				// PorterDuff.Mode.CLEAR);
			}
		});

		try {
			mPlayer.setDataSource(fileName);
			mPlayer.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mPlayer.start();
	}

	public void stop() {
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}
	
	public void release()
	{
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}
}
