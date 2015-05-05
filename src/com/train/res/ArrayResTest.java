package com.train.res;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.train.R;

@SuppressLint("NewApi")
public class ArrayResTest extends Activity
{
	//��ȡϵͳ�����������Դ
	String[] texts;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.valueres);
		texts = getResources().getStringArray(R.array.string_arr);
		//����һ��BaseAdapter����
		BaseAdapter ba = new BaseAdapter()
		{
			@Override
			public int getCount()
			{
				//ָ��һ������9��ѡ��
				return texts.length;
			}

			@Override
			public Object getItem(int position)
			{
				//����ָ��λ�õ��ı�
				return texts[position];
			}

			@Override
			public long getItemId(int position)
			{
				return position;
			}
			//��д�÷������÷������ص�View����Ϊ��GridView��ÿ������
			@Override
			public View getView(int position, View convertView, ViewGroup parent) 
			{
				TextView text = new TextView(ArrayResTest.this);
				Resources res = ArrayResTest.this.getResources();
				//ʹ�ó߶���Դ�������ı���ĸ߶ȡ����
				text.setWidth((int) res.getDimension(R.dimen.cell_width));
				text.setHeight((int) res.getDimension(R.dimen.cell_height));
				//ʹ���ַ�����Դ�����ı��������
				text.setText(texts[position]);
				TypedArray icons = res.obtainTypedArray(R.array.plain_arr); 
				//ʹ����ɫ��Դ�������ı���� ����ɫ
				text.setBackground(icons.getDrawable(position));
				
				text.setTextSize(20);			
				return text;
			}
		};
		GridView grid = (GridView)findViewById(R.id.grid01);
		//ΪGridView����Adapter
		grid.setAdapter(ba);
	}
}