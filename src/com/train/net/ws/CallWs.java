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
//			// 调用Web Service
//			ht.call(null,  envelope);
//			if (envelope.getResponse() != null)
//			{
//				// 获取服务器响应返回的SOAP消息
//				SoapObject result = (SoapObject) envelope.bodyIn;
//				// 接下来就是从SoapObject对象中解析响应数据的过程了。
//				SoapObject detail1 = (SoapObject) result.getProperty(0);
//				SoapObject detail2 = (SoapObject) result.getProperty(1);
//				StringBuilder person1 = new StringBuilder();
//				person1.append("用户名：");
//				person1.append(detail1.getProperty(3));
//				person1.append("\n密码");
//				person1.append(detail1.getProperty(0));
//				person1.append("\n身高：");
//				person1.append(detail1.getProperty(1));	
//				txt1.setText(person1.toString());
//				StringBuilder person2 = new StringBuilder();
//				person2.append("用户名：");
//				person2.append(detail2.getProperty(3));
//				person2.append("\n密码：");
//				person2.append(detail2.getProperty(0));
//				person2.append("\n身高：");
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
		// onPreExecute方法用于在执行后台任务前做一些UI操作
		@Override
		protected void onPreExecute() {
			Log.i(TAG, "onPreExecute() called");

		}

		// doInBackground方法内部执行后台任务,不可在此方法内修改UI
		@Override
		protected String doInBackground(String... params) {
			Log.i(TAG, "doInBackground(Params... params) called");
			// 调用的方法
			String methodName = "getUserList";
			// 创建HttpTransportSE传输对象
			HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
			ht.debug = true;
			// 使用SOAP1.1协议创建Envelop对象
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11 );		
			// 实例化SoapObject对象
			SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
			soapObject.addProperty("arg0", "客户端参数:");
			// 将soapObject对象设置为 SoapSerializationEnvelope对象的传出SOAP消息
			envelope.bodyOut = soapObject;
			try
			{
				// 调用Web Service
				ht.call(null,  envelope);
				if (envelope.getResponse() != null)
				{
					// 获取服务器响应返回的SOAP消息
					SoapObject result = (SoapObject) envelope.bodyIn;
					// 接下来就是从SoapObject对象中解析响应数据的过程了。
					SoapObject detail1 = (SoapObject) result.getProperty(0);
					SoapObject detail2 = (SoapObject) result.getProperty(1);
					StringBuilder person1 = new StringBuilder();
					person1.append("用户名：");
					person1.append(detail1.getProperty(3));
					person1.append("\n密码");
					person1.append(detail1.getProperty(0));
					person1.append("\n身高：");
					person1.append(detail1.getProperty(1));	
					
					StringBuilder person2 = new StringBuilder();
					person2.append("用户名：");
					person2.append(detail2.getProperty(3));
					person2.append("\n密码：");
					person2.append(detail2.getProperty(0));
					person2.append("\n身高：");
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

		// onProgressUpdate方法用于更新进度信息
		@Override
		protected void onProgressUpdate(Integer... progresses) {
			Log.i(TAG, "onProgressUpdate(Progress... progresses) called");

		}

		// onPostExecute方法用于在执行完后台任务后更新UI,显示结果
		@Override
		protected void onPostExecute(String result) {
			Log.i(TAG, "onPostExecute(Result result) called");
			// 提示登录成功
			;
			txt1.setText(result.substring(0,result.indexOf("|")));	
			txt2.setText(result.substring(result.indexOf("|")+1));		
			
		}

		// onCancelled方法用于在取消执行中的任务时更改UI
		@Override
		protected void onCancelled() {
			Log.i(TAG, "onCancelled() called");

		}
	}
}