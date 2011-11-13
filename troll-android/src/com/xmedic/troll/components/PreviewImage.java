package com.xmedic.troll.components;

import com.xmedic.troll.service.MapMath;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class PreviewImage extends ImageView {

	private Point startPoint;
	
	private Point endPoint;

	private Paint blackPaint;
	
	public PreviewImage(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		 blackPaint = new Paint();
		 blackPaint.setColor(Color.BLACK);
	}
	
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if(startPoint != null && endPoint != null) {
			
			Log.d("START", startPoint.toString());
			Point coordinates = MapMath.toDrawPoint(new Point((int)(startPoint.x / 3.33176), (int)(startPoint.y / 3.331275)), 0, 0);
			canvas.drawCircle(coordinates.x, coordinates.y, 4, blackPaint);
			Log.d("COORD", coordinates.toString());
			
			coordinates = MapMath.toDrawPoint(new Point((int)(endPoint.x / 3.33176), (int)(endPoint.y / 3.331275)), 0, 0);
			canvas.drawCircle(coordinates.x, coordinates.y, 4, blackPaint);
			Log.d("START", endPoint.toString());
			Log.d("COORD", coordinates.toString());
		}
	}

}
