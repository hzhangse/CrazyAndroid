package com.train.media.capture;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.train.R;

public class CaptureImage extends Activity {
	SurfaceView sView;
	SurfaceHolder surfaceHolder;
	int screenWidth, screenHeight;
	// ����ϵͳ���õ������
	Camera camera;
	// �Ƿ��������
	boolean isPreview = false;
	boolean aIsDown = false;
	boolean qIsDown = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ����ȫ��
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
		setContentView(R.layout.capture_main);
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		// ��ȡ��Ļ�Ŀ�͸�
		screenWidth = display.getWidth();
		screenHeight = display.getHeight();
		// ��ȡ������SurfaceView���
		sView = (SurfaceView) findViewById(R.id.sView);
		// ���SurfaceView��SurfaceHolder
		surfaceHolder = sView.getHolder();
		sView.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View arg0, MotionEvent event)
			{
				
				if (camera != null ) {
					// ����
					camera.takePicture(null, null, myjpegCallback);
					return true;
				}
				return true;
			}		
		});
		// ΪsurfaceHolder���һ���ص�������
		surfaceHolder.addCallback(new Callback() {
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// ������ͷ
				initCamera();
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// ���camera��Ϊnull ,�ͷ�����ͷ
				if (camera != null) {
					if (isPreview)
						camera.stopPreview();
					camera.release();
					camera = null;
				}
			}
		});
		// ���ø�SurfaceView�Լ���ά������
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	private void initCamera() {
		if (!isPreview) {
			camera = Camera.open();
		}
		if (camera != null && !isPreview) {
			try {
				Camera.Parameters parameters = camera.getParameters();
				// ����Ԥ����Ƭ�Ĵ�С
				parameters.setPreviewSize(screenWidth, screenHeight);
				// ÿ����ʾ4֡
				parameters.setPreviewFrameRate(4);
				// ����ͼƬ��ʽ
				parameters.setPictureFormat(PixelFormat.JPEG);
				// ����JPG��Ƭ������
				parameters.set("jpeg-quality", 85);
				// ������Ƭ�Ĵ�С
				parameters.setPictureSize(screenWidth, screenHeight);
				camera.setParameters(parameters);
				// ͨ��SurfaceView��ʾȡ������
				camera.setPreviewDisplay(surfaceHolder);
				// ��ʼԤ��
				camera.startPreview();
				// �Զ��Խ�
				camera.autoFocus(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			isPreview = true;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		// ���û�����������������ʱִ������
		case KeyEvent.KEYCODE_DPAD_CENTER:
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			if (camera != null && event.getRepeatCount() == 0) {
				// ����
				camera.takePicture(null, null, myjpegCallback);
				return true;
			}
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	PictureCallback myjpegCallback = new PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// �����������õ����ݴ���λͼ
			final Bitmap bm = BitmapFactory.decodeByteArray(data, 0,
					data.length);
			// ����/layout/save.xml�ļ���Ӧ�Ĳ�����Դ
			View saveDialog = getLayoutInflater().inflate(
					R.layout.capture_save, null);
			final EditText photoName = (EditText) saveDialog
					.findViewById(R.id.phone_name);
			// ��ȡsaveDialog�Ի����ϵ�ImageView���
			ImageView show = (ImageView) saveDialog.findViewById(R.id.show);
			// ��ʾ�ո��ĵõ���Ƭ
			show.setImageBitmap(bm);
			// ʹ�öԻ�����ʾsaveDialog���
			new AlertDialog.Builder(CaptureImage.this).setView(saveDialog)
					.setPositiveButton("����", new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// ����һ��λ��SD���ϵ��ļ�
							File file = new File(Environment
									.getExternalStorageDirectory(), photoName
									.getText().toString() + ".jpg");
							FileOutputStream outStream = null;
							try {
								// ��ָ���ļ���Ӧ�������
								outStream = new FileOutputStream(file);
								// ��λͼ�����ָ���ļ���
								bm.compress(CompressFormat.JPEG, 100, outStream);
								outStream.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}).setNegativeButton("ȡ��", null).show();
			// �������
			camera.stopPreview();
			camera.startPreview();
			isPreview = true;
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#dispatchKeyEvent(android.view.KeyEvent)
	 */
//	public boolean dispatchKeyEvent(KeyEvent event) {
//		// TODO Auto-generated method stub
//		// �ж���ͨ����
//		int keyCode = event.getKeyCode();
//		if (keyCode == KeyEvent.KEYCODE_POWER
//				&& event.getAction() == KeyEvent.ACTION_DOWN) {
//			aIsDown = true;
//		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN
//				&& event.getAction() == KeyEvent.ACTION_DOWN) {
//			qIsDown = true;
//		} else if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN && !aIsDown)
//				|| (keyCode == KeyEvent.KEYCODE_POWER && !qIsDown)) {
//			aIsDown = false;
//			qIsDown = false;
//		} else if (aIsDown
//				&& qIsDown
//				&& (keyCode == KeyEvent.KEYCODE_POWER || keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)
//				&& event.getAction() == KeyEvent.ACTION_UP) {
//			Toast.makeText(this, "Q + A", 0).show();
//			aIsDown = false;
//			qIsDown = false;
//		} else {
//			aIsDown = false;
//			qIsDown = false;
//		}
//		return super.dispatchKeyEvent(event);
//	}
}
