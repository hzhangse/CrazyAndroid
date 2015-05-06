package com.train.sensor;

//import org.openintents.sensorsimulator.hardware.Sensor;
//import org.openintents.sensorsimulator.hardware.SensorEvent;
//import org.openintents.sensorsimulator.hardware.SensorEventListener;
//import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.train.R;


public class Compass extends Activity
	implements SensorEventListener
{
	// ������ʾָ�����ͼƬ
	ImageView znzImage;
	// ��¼ָ����ͼƬת���ĽǶ�
	float currentDegree = 0f;
	// ����ģ������Sensor������
//	private SensorManagerSimulator mSensorManager;

	 // ���������Sensor������
	SensorManager mSensorManager;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compass_main);
		// ��ȡ��������ʾָ�����ͼƬ
		znzImage = (ImageView) findViewById(R.id.znzImage);

		 // ��ȡ����Ĵ�������������
		 mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
//		// ��ȡ������ģ�����Ĵ�������������
//		mSensorManager = SensorManagerSimulator.getSystemService(this,
//			SENSOR_SERVICE);
//		// ���Ӵ�����ģ����
//		mSensorManager.connectSimulator();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		// Ϊϵͳ�ķ��򴫸���ע�������
		mSensorManager.registerListener(this,
			mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
			SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onPause()
	{
		// ȡ��ע��
		mSensorManager.unregisterListener(this);
		super.onPause();
	}

	@Override
	protected void onStop()
	{
		// ȡ��ע��
		mSensorManager.unregisterListener(this);
		super.onStop();
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		// ����ϻ�ȡ����event�Ĵ���������
		int sensorType = event.sensor.getType();
//		// ģ�����ϻ�ȡ����event�Ĵ���������
//		int sensorType = event.type;
		switch (sensorType)
		{
			case Sensor.TYPE_ORIENTATION:
				// ��ȡ��Z��ת���ĽǶȡ�
				float degree = event.values[0];
				// ������ת����������ת��degree�ȣ�
				RotateAnimation ra = new RotateAnimation(currentDegree,
					-degree, Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
				// ���ö����ĳ���ʱ��
				ra.setDuration(200);
				// ���ж���
				znzImage.startAnimation(ra);
				currentDegree = -degree;
				break;
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
	}
}