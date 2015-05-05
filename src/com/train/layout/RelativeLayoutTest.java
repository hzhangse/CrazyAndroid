package com.train.layout;

import android.app.Activity;
import android.os.Bundle;

import com.train.R;


public class RelativeLayoutTest extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relativelayout);
		findViewById(R.id.view01).setPadding(5, 5 , 5 , 5);
		findViewById(R.id.view02).setPadding(5, 5 , 5 , 5);
		findViewById(R.id.view03).setPadding(5, 5 , 5 , 5);
		findViewById(R.id.view04).setPadding(5, 5 , 5 , 5);
		findViewById(R.id.view05).setPadding(5, 5 , 5 , 5);
	}
}