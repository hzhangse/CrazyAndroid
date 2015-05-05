package com.train.net.httpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.train.R;

public class HttpClientTest extends Activity {
	private static final String TAG = "HttpClientTest";
	Button get;
	Button login;
	EditText response;
	HttpClient httpClient;
	private MyTask mTask;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpclient_main);
		// ����DefaultHttpClient����
		httpClient = new DefaultHttpClient();
		get = (Button) findViewById(R.id.get);
		login = (Button) findViewById(R.id.login);
		response = (EditText) findViewById(R.id.response);
		get.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ����һ��HttpGet����
				mTask = new MyTask();
				mTask.execute("http://192.168.1.101:8080/foo/secret.jsp");
			}
		});
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final View loginDialog = getLayoutInflater().inflate(
						R.layout.httpclientlogin, null);
				new AlertDialog.Builder(HttpClientTest.this)
						.setTitle("��¼ϵͳ")
						.setView(loginDialog)
						.setPositiveButton("��¼",
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										String name = ((EditText) loginDialog
												.findViewById(R.id.name))
												.getText().toString();
										String pass = ((EditText) loginDialog
												.findViewById(R.id.pass))
												.getText().toString();
										loginTask task = new loginTask();
										String url ="http://192.168.1.101:8080/foo/login.jsp";
										task.execute(url,name,pass);
									}
								}).setNegativeButton("ȡ��", null).show();
			}
		});
	}

	private class MyTask extends AsyncTask<String, Integer, String> {
		// onPreExecute����������ִ�к�̨����ǰ��һЩUI����
		@Override
		protected void onPreExecute() {
			Log.i(TAG, "onPreExecute() called");

		}

		// doInBackground�����ڲ�ִ�к�̨����,�����ڴ˷������޸�UI
		@Override
		protected String doInBackground(String... params) {
			Log.i(TAG, "doInBackground(Params... params) called");
			HttpGet get = new HttpGet(params[0]);
			try {
				// ����GET����
				HttpResponse httpResponse = httpClient.execute(get);
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					// ��ȡ��������Ӧ
					BufferedReader br = new BufferedReader(
							new InputStreamReader(entity.getContent()));
					String line = null;
					StringBuilder builder = new StringBuilder();
					
					while ((line = br.readLine()) != null) {
						// ʹ��response�ı�����ʾ��������Ӧ
						builder.append(line + "\n");
						
					}
					return builder.toString();
				}
			} catch (Exception e) {
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
			response.append(result);
		}

		// onCancelled����������ȡ��ִ���е�����ʱ����UI
		@Override
		protected void onCancelled() {
			Log.i(TAG, "onCancelled() called");

		}
	}
	
	private class loginTask extends AsyncTask<String, Integer, String> {
		// onPreExecute����������ִ�к�̨����ǰ��һЩUI����
		@Override
		protected void onPreExecute() {
			Log.i(TAG, "onPreExecute() called");

		}

		// doInBackground�����ڲ�ִ�к�̨����,�����ڴ˷������޸�UI
		@Override
		protected String doInBackground(String... params) {
			Log.i(TAG, "doInBackground(Params... params) called");
			HttpPost post = new HttpPost(
					params[0]);
			// ������ݲ��������Ƚ϶�Ļ����ԶԴ��ݵĲ������з�װ
			List<NameValuePair> httpParams = new ArrayList<NameValuePair>();
			httpParams.add(new BasicNameValuePair(
					"name", params[1]));
			httpParams.add(new BasicNameValuePair(
					"pass", params[2]));
			try {
				// �����������
				post.setEntity(new UrlEncodedFormEntity(
						httpParams, HTTP.UTF_8));
				// ����POST����
				HttpResponse response = httpClient
						.execute(post);
				// ����������ɹ��ط�����Ӧ
				if (response.getStatusLine()
						.getStatusCode() == 200) {
					String msg = EntityUtils
							.toString(response
					
									.getEntity());
					return msg;
					
				}
			} catch (Exception e) {
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
			Toast.makeText(
					HttpClientTest.this,
					result, 5000).show();

		}

		// onCancelled����������ȡ��ִ���е�����ʱ����UI
		@Override
		protected void onCancelled() {
			Log.i(TAG, "onCancelled() called");

		}
	}
}