/**
 * 
 */
package com.train.event;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.train.R;

public class PlaneView extends View
{
	public float currentX;
	public float currentY;
	Bitmap plane;
	/**
	 * @param context
	 */
	public PlaneView(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
		//����ɻ�ͼƬ
		plane = BitmapFactory.decodeResource(context.getResources()
			, R.drawable.plane);
		setFocusable(true);
	}
	@Override
	public void onDraw (Canvas canvas)
	{
		super.onDraw(canvas);
		//��������
		Paint p = new Paint();
		//���Ʒɻ�
		canvas.drawBitmap(plane , currentX , currentY , p);	
	}
}

