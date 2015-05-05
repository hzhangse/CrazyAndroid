package com.train.media.video;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.media.MediaRecorder;
import android.os.Environment;
import android.view.SurfaceView;

public class MovieRecorder {
	private MediaRecorder mediarecorder;
	boolean isRecording;

	public void startRecording(SurfaceView surfaceView) {
		mediarecorder = new MediaRecorder();// 创建mediarecorder对象
		// 设置从麦克风采集声音
		mediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		// 设置录制视频源为Camera(相机)
		mediarecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
		// 设置录制完成后视频的封装格式THREE_GPP�?gp.MPEG_4为mp4
		//mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mediarecorder.setOutputFormat(MediaRecorder
				.OutputFormat.MPEG_4);			
		// 设置声音编码的格�?
				mediarecorder.setAudioEncoder(MediaRecorder
						.AudioEncoder.DEFAULT);
		// 设置录制的视频编码h263 h264
	//	mediarecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
		mediarecorder.setVideoEncoder(MediaRecorder
				.VideoEncoder.MPEG_4_SP); 
		// 设置视频录制的分辨率。必须放在设置编码和格式的后面，否则报错
		mediarecorder.setVideoSize(176, 144);
		// 设置录制的视频帧率�?必须放在设置编码和格式的后面，否则报�?
		mediarecorder.setVideoFrameRate(20);
		mediarecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
		// 设置视频文件输出的路�?
		lastFileName = newFileName();
		mediarecorder.setOutputFile(lastFileName);
		try {
			// 准备录制
			mediarecorder.prepare();
			// �?��录制
			mediarecorder.start();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isRecording = true;
		timeSize = 0;
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				timeSize++;
			}
		}, 0, 1000);
	}

	Timer timer;
	int timeSize = 0;

	private String lastFileName;

	public void stopRecording() {
		if (mediarecorder != null) {
			// 停止
			mediarecorder.stop();
			mediarecorder.release();
			mediarecorder = null;

			timer.cancel();
			if (null != lastFileName && !"".equals(lastFileName)) {
				File f = new File(lastFileName);
				String name = f.getName().substring(0,
						f.getName().lastIndexOf(".3gp"));
				name += "_" + timeSize + "s.3gp";
				String mFileName = Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/movie";
				if (!new File(mFileName).exists()) {
					new File(mFileName).mkdir();
				} else {
					String newPath = mFileName + "/" + name;
					
					if (f.renameTo(new File(newPath))) {
						int i = 0;
						i++;
					}
				}
			}
		}
	}

	public String newFileName() {
		try {
			String mFileName = Environment.getExternalStorageDirectory()
					.getAbsolutePath();
			return File.createTempFile("/mov_", ".3gp",new File(mFileName)).getAbsolutePath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void release() {
		if (mediarecorder != null) {
			// 停止
			mediarecorder.stop();
			mediarecorder.release();
			mediarecorder = null;
		}
	}
}
