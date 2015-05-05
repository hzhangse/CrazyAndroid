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
		// 创建DefaultHttpClient对象
		httpClient = new DefaultHttpClient();
		get = (Button) findViewById(R.id.get);
		login = (Button) findViewById(R.id.login);
		response = (EditText) findViewById(R.id.response);
		get.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 创建一个HttpGet对象
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
						.setTitle("登录系统")
						.setView(loginDialog)
						.setPositiveButton("登录",
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
								}).setNegativeButton("取消", null).show();
			}
		});
	}

	private class MyTask extends AsyncTask<String, Integer, String> {
		// onPreExecute方法用于在执行后台任务前做一些UI操作
		@Override
		protected void onPreExecute() {
			Log.i(TAG, "onPreExecute() called");

		}

		// doInBackground方法内部执行后台任务,不可在此方法内修改UI
		@Override
		protected String doInBackground(String... params) {
			Log.i(TAG, "doInBackground(Params... params) called");
			HttpGet get = new HttpGet(params[0]);
			try {
				// 发送GET请求
				HttpResponse httpResponse = httpClient.execute(get);
				HttpEntity entity = httpResponse.getEntity();
				if (entity != null) {
					// 读取服务器响应
					BufferedReader br = new BufferedReader(
							new InputStreamReader(entity.getContent()));
					String line = null;
					StringBuilder builder = new StringBuilder();
					
					while ((line = br.readLine()) != null) {
						// 使用response文本框显示服务器响应
						builder.append(line + "\n");
						
					}
					return builder.toString();
				}
			} catch (Exception e) {
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
			response.append(result);
		}

		// onCancelled方法用于在取消执行中的任务时更改UI
		@Override
		protected void onCancelled() {
			Log.i(TAG, "onCancelled() called");

		}
	}
	
	private class loginTask extends AsyncTask<String, Integer, String> {
		// onPreExecute方法用于在执行后台任务前做一些UI操作
		@Override
		protected void onPreExecute() {
			Log.i(TAG, "onPreExecute() called");

		}

		// doInBackground方法内部执行后台任务,不可在此方法内修改UI
		@Override
		protected String doInBackground(String... params) {
			Log.i(TAG, "doInBackground(Params... params) called");
			HttpPost post = new HttpPost(
					params[0]);
			// 如果传递参数个数比较多的话可以对传递的参数进行封装
			List<NameValuePair> httpParams = new ArrayList<NameValuePair>();
			httpParams.add(new BasicNameValuePair(
					"name", params[1]));
			httpParams.add(new BasicNameValuePair(
					"pass", params[2]));
			try {
				// 设置请求参数
				post.setEntity(new UrlEncodedFormEntity(
						httpParams, HTTP.UTF_8));
				// 发送POST请求
				HttpResponse response = httpClient
						.execute(post);
				// 如果服务器成功地返回响应
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
			Toast.makeText(
					HttpClientTest.this,
					result, 5000).show();

		}

		// onCancelled方法用于在取消执行中的任务时更改UI
		@Override
		protected void onCancelled() {
			Log.i(TAG, "onCancelled() called");

		}
	}
}