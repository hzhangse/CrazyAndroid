package com.train.net.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.train.R;
import com.train.net.httpclient.HttpClientTest;


public class CallWs extends Activity
{
	final static String SERVICE_NS = "http://ws.train.com/";
	final static String SERVICE_URL = "http://192.168.1.101:9999/ws";
	private EditText txt1;
	private EditText txt2;
	String TAG ="Call Ws";
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.callws);
		txt1 = (EditText) findViewById(R.id.txt1);
		txt2 = (EditText) findViewById(R.id.txt2);
		
		CallWsTask task = new CallWsTask();
		task.execute();
//		try
//		{
//			// ����Web Service
//			ht.call(null,  envelope);
//			if (envelope.getResponse() != null)
//			{
//				// ��ȡ��������Ӧ���ص�SOAP��Ϣ
//				SoapObject result = (SoapObject) envelope.bodyIn;
//				// ���������Ǵ�SoapObject�����н�����Ӧ���ݵĹ����ˡ�
//				SoapObject detail1 = (SoapObject) result.getProperty(0);
//				SoapObject detail2 = (SoapObject) result.getProperty(1);
//				StringBuilder person1 = new StringBuilder();
//				person1.append("�û�����");
//				person1.append(detail1.getProperty(3));
//				person1.append("\n����");
//				person1.append(detail1.getProperty(0));
//				person1.append("\n��ߣ�");
//				person1.append(detail1.getProperty(1));	
//				txt1.setText(person1.toString());
//				StringBuilder person2 = new StringBuilder();
//				person2.append("�û�����");
//				person2.append(detail2.getProperty(3));
//				person2.append("\n���룺");
//				person2.append(detail2.getProperty(0));
//				person2.append("\n��ߣ�");
//				person2.append(detail2.getProperty(1));	
//				txt2.setText(person2.toString());				
//			}
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//		catch (XmlPullParserException e)
//		{
//			e.printStackTrace();
//		}
	}
	private class CallWsTask extends AsyncTask<String, Integer, String> {
		// onPreExecute����������ִ�к�̨����ǰ��һЩUI����
		@Override
		protected void onPreExecute() {
			Log.i(TAG, "onPreExecute() called");

		}

		// doInBackground�����ڲ�ִ�к�̨����,�����ڴ˷������޸�UI
		@Override
		protected String doInBackground(String... params) {
			Log.i(TAG, "doInBackground(Params... params) called");
			// ���õķ���
			String methodName = "getUserList";
			// ����HttpTransportSE�������
			HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
			ht.debug = true;
			// ʹ��SOAP1.1Э�鴴��Envelop����
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11 );		
			// ʵ����SoapObject����
			SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
			soapObject.addProperty("arg0", "�ͻ��˲���:");
			// ��soapObject��������Ϊ SoapSerializationEnvelope����Ĵ���SOAP��Ϣ
			envelope.bodyOut = soapObject;
			try
			{
				// ����Web Service
				ht.call(null,  envelope);
				if (envelope.getResponse() != null)
				{
					// ��ȡ��������Ӧ���ص�SOAP��Ϣ
					SoapObject result = (SoapObject) envelope.bodyIn;
					// ���������Ǵ�SoapObject�����н�����Ӧ���ݵĹ����ˡ�
					SoapObject detail1 = (SoapObject) result.getProperty(0);
					SoapObject detail2 = (SoapObject) result.getProperty(1);
					StringBuilder person1 = new StringBuilder();
					person1.append("�û�����");
					person1.append(detail1.getProperty(3));
					person1.append("\n����");
					person1.append(detail1.getProperty(0));
					person1.append("\n��ߣ�");
					person1.append(detail1.getProperty(1));	
					
					StringBuilder person2 = new StringBuilder();
					person2.append("�û�����");
					person2.append(detail2.getProperty(3));
					person2.append("\n���룺");
					person2.append(detail2.getProperty(0));
					person2.append("\n��ߣ�");
					person2.append(detail2.getProperty(1));	
					return person1.toString()+ "|"+person2.toString();
				
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (XmlPullParserException e)
			{
				e.printStackTrace();
			}
			return null;
		}

		// onProgressUpdate�������ڸ��½�����Ϣ
		@Override
		protected void onProgressUpdate(Integer... progresses) {
			Log.i(TAG, "onProgressUpdate(Progress... progresses) called");

		}

		// onPostExecute����������ִ�����̨��������UI,��ʾ���
		@Override
		protected void onPostExecute(String result) {
			Log.i(TAG, "onPostExecute(Result result) called");
			// ��ʾ��¼�ɹ�
			;
			txt1.setText(result.substring(0,result.indexOf("|")));	
			txt2.setText(result.substring(result.indexOf("|")+1));		
			
		}

		// onCancelled����������ȡ��ִ���е�����ʱ����UI
		@Override
		protected void onCancelled() {
			Log.i(TAG, "onCancelled() called");

		}
	}
}