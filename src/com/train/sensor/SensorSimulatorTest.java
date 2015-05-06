package com.train.sensor;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.EditText;

import com.train.R;


public class SensorSimulatorTest extends Activity
	implements SensorEventListener
{
	// // ���������Sensor������
	private SensorManager mSensorManager;
	// ����ģ������Sensor������
	//private SensorManagerSimulator mSensorManager;

	EditText etOrientation;
	EditText etMagnetic;
	EditText etTemerature;
	EditText etLight;
	EditText etPressure;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensor_main);
		// ��ȡ�����ϵ�EditText���
		etOrientation = (EditText) findViewById(R.id.etOrientation);
		etMagnetic = (EditText) findViewById(R.id.etMagnetic);
		etTemerature = (EditText) findViewById(R.id.etTemerature);
		etLight = (EditText) findViewById(R.id.etLight);
		etPressure = (EditText) findViewById(R.id.etPressure);
		// ��ȡ����Ĵ������������
		 mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
		// ��ȡ������ģ�����Ĵ������������
//		mSensorManager = SensorManagerSimulator.getSystemService(this,
//			SENSOR_SERVICE);
		// ���Ӵ�����ģ����
		//mSensorManager.connectSimulator();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		// Ϊϵͳ�ķ��򴫸���ע�������
		mSensorManager.registerListener(this,
			mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
			SensorManager.SENSOR_DELAY_GAME);
		// Ϊϵͳ�Ĵų�������ע�������
		mSensorManager.registerListener(this,
			mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
			SensorManager.SENSOR_DELAY_GAME);
		// Ϊϵͳ���¶ȴ�����ע�������
		mSensorManager.registerListener(this,
			mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),
			SensorManager.SENSOR_DELAY_GAME);
		// Ϊϵͳ�Ĺ⴫����ע�������
		mSensorManager.registerListener(this,
			mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
			SensorManager.SENSOR_DELAY_GAME);
		// Ϊϵͳ��ѹ��������ע�������
		mSensorManager.registerListener(this,
			mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),
			SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onStop()
	{
		// �����˳�ʱȡ��ע�ᴫ����������
		mSensorManager.unregisterListener(this);
		super.onStop();
	}

	@Override
	protected void onPause()
	{
		// ������ͣʱȡ��ע�ᴫ����������
		mSensorManager.unregisterListener(this);
		super.onPause();
	}

	// ������ʵ��SensorEventListener�ӿڱ���ʵ�ֵķ���
	@Override
	// �����������ȸı�ʱ�ص��÷�����
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		float[] values = event.values;
		// // ����ϻ�ȡ����event�Ĵ���������
		 int sensorType = event.sensor.getType();
		// ģ�����ϻ�ȡ����event�Ĵ���������
		//int sensorType = event.type;
		StringBuilder sb = null;
		// �ж����ĸ������������ı�
		switch (sensorType)
		{
			// ���򴫸���
			case Sensor.TYPE_ORIENTATION:
				sb = new StringBuilder();
				sb.append("��Z��ת���ĽǶȣ�");
				sb.append(values[0]);
				sb.append("\n��X��ת���ĽǶȣ�");
				sb.append(values[1]);
				sb.append("\n��Y��ת���ĽǶȣ�");
				sb.append(values[2]);
				etOrientation.setText(sb.toString());
				break;
			// �ų�������
			case Sensor.TYPE_MAGNETIC_FIELD:
				sb = new StringBuilder();
				sb.append("X�����ϵĽǶȣ�");
				sb.append(values[0]);
				sb.append("\nY�����ϵĽǶȣ�");
				sb.append(values[1]);
				sb.append("\nZ�����ϵĽǶȣ�");
				sb.append(values[2]);
				etMagnetic.setText(sb.toString());
				break;
			// �¶ȴ�����
			case Sensor.TYPE_AMBIENT_TEMPERATURE:
				sb = new StringBuilder();
				sb.append("��ǰ�¶�Ϊ��");
				sb.append(values[0]);
				etTemerature.setText(sb.toString());
				break;
			// �⴫����
			case Sensor.TYPE_LIGHT:
				sb = new StringBuilder();
				sb.append("��ǰ���ǿ��Ϊ��");
				sb.append(values[0]);
				etLight.setText(sb.toString());
				break;
			// ѹ��������
			case Sensor.TYPE_PRESSURE:
				sb = new StringBuilder();
				sb.append("��ǰѹ��Ϊ��");
				sb.append(values[0]);
				etPressure.setText(sb.toString());
				break;
		}
	}
}