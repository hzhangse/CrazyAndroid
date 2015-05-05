package com.train.media.video;

import java.io.File;
import java.io.FileFilter;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.train.R;

public class MainActivity extends Activity {
	private static final String LOG_TAG = MainActivity.class.getSimpleName();

	MoviePlayer mPlayer;
	MovieRecorder mRecorder;

	Button btnRecoder;

	ListView mListView1;
	SurfaceView surfaceView;
	SurfaceHolder surfaceHolder;
	MyAdatpter myAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// ����ȫ��
		// ���ú�����ʾ
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		// ѡ��֧�ְ�͸��ģʽ,����surfaceview��activity��ʹ�á�
		getWindow().setFormat(PixelFormat.TRANSLUCENT);

		setContentView(R.layout.video_activity_main);

		btnRecoder = (Button) findViewById(R.id.btnRecoder);
		btnRecoder.setOnClickListener(mRecordingClick);

		surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(surfaceHolderCallback); // holder����ص��ӿ�
		// setType�������ã�Ҫ������.
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		mPlayer = new MoviePlayer();
		mRecorder = new MovieRecorder();

		mListView1 = (ListView) findViewById(R.id.listView1);
		myAdapter = new MyAdatpter();
		mListView1.setAdapter(myAdapter);
		mListView1
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						File f = myAdapter.lst[arg2];
						mPlayer.play(f.getAbsolutePath(),
								surfaceView);
					}
				});

		refreshViewByRecordingState();
	}

	protected void onDestroy() {
		if (mRecorder != null) {
			mRecorder.release();
		}
		if (mPlayer != null) {
			mPlayer.release();
		}
		super.onDestroy();
	};

	Callback surfaceHolderCallback = new Callback() {

		@Override
		public void surfaceDestroyed(SurfaceHolder arg0) {
			surfaceView = null;
			surfaceHolder = null;
			mRecorder = null;
		}

		@Override
		public void surfaceCreated(SurfaceHolder arg0) {
			// TODO Auto-generated method stub
			surfaceHolder = arg0;
		}

		@Override
		public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			surfaceHolder = arg0;
		}
	};

	OnClickListener mRecordingClick = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			if (!mRecorder.isRecording) {
				mRecorder.startRecording(surfaceView);

				mRecorder.isRecording = true;
				btnRecoder.setText("¼���У�����ֹͣ");
			} else {
				mRecorder.stopRecording();

				mRecorder.isRecording = false;
				btnRecoder.setText("¼��¼����������ʼ");

				refreshViewByRecordingState();
			}

		}
	};


	
	/* ˢ��״̬ */
	protected void refreshViewByRecordingState() {
		if (mRecorder.isRecording) {
			mRecorder.isRecording = true;
			btnRecoder.setText("¼���У�����ֹͣ");
		} else {
			mRecorder.isRecording = false;
			btnRecoder.setText("׼��¼�ƣ�������ʼ");
		}

		myAdapter.query();
		myAdapter.notifyDataSetChanged();
	}

	class MyAdatpter extends BaseAdapter {
		File[] lst;

		public MyAdatpter() {
			super();
			query();
		}

		public void query() {
			String mFileName = Environment.getExternalStorageDirectory()
					.getAbsolutePath()+"/movie";
			File f = new File(mFileName);
			File[] found = f.listFiles(new FileFilter() {

				@Override
				public boolean accept(File pathname) {
					if (pathname.getName().startsWith("mov_")) {
						return true;
					}
					return false;
				}
			});

			lst = new File[found.length];
			for (int i = 0; i < found.length; i++) {
				lst[i] = found[found.length - i - 1];
			}
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return lst == null ? 0 : lst.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			View v = getLayoutInflater().inflate(R.layout.viedo_item, null);
			TextView txtText = (TextView) v.findViewById(R.id.txtText);
			TextView txtSize = (TextView) v.findViewById(R.id.txtSize);

			File f = lst[arg0];
			txtText.setText(f.getName());
			txtSize.setText(f.length() / 1024 + " kb");

			return v;
		}

	}
}
