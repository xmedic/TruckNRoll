package com.xmedic.troll.components;

import com.xmedic.troll.service.MapMath;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
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

	private Paint linePaint;
	
	public PreviewImage(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		 blackPaint = new Paint();
		 blackPaint.setColor(Color.BLACK);
		 
		 linePaint = new Paint();
		 linePaint.setColor(Color.GRAY);
		 linePaint.setStrokeWidth(5);
		 linePaint.setAntiAlias(true);
		 linePaint.setPathEffect(new DashPathEffect(new float[] {10, 10}, 0));
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
			Point coordinatesStart = MapMath.toDrawPoint(new Point((int)(startPoint.x / 3.33176), (int)(startPoint.y / 3.331275)), 0, 0);
			canvas.drawCircle(coordinatesStart.x, coordinatesStart.y, 4, blackPaint);
			Log.d("COORD", coordinatesStart.toString());
			
			Point coordinatesGoal = MapMath.toDrawPoint(new Point((int)(endPoint.x / 3.33176), (int)(endPoint.y / 3.331275)), 0, 0);
			canvas.drawCircle(coordinatesGoal.x, coordinatesGoal.y, 4, blackPaint);
			Log.d("START", endPoint.toString());
			Log.d("COORD", coordinatesGoal.toString());
			
			 
			 canvas.drawLine(coordinatesStart.x, coordinatesStart.y, coordinatesGoal.x, coordinatesGoal.y, linePaint);

		}
	}

}
