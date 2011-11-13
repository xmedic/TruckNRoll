package com.xmedic.troll.components;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.xmedic.troll.R;
import com.xmedic.troll.service.MapMath;

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
		 linePaint.setColor(Color.BLACK);
		 linePaint.setStrokeWidth(1);
		 linePaint.setAntiAlias(true);
		 linePaint.setPathEffect(new DashPathEffect(new float[] {2, 2}, 0));
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
			
			Point coordinatesStart = MapMath.toPreviewDrawPoint(startPoint);
			Point coordinatesGoal = MapMath.toPreviewDrawPoint(endPoint);

			canvas.drawLine(coordinatesStart.x, coordinatesStart.y, coordinatesGoal.x, coordinatesGoal.y, linePaint);
			
			Log.d("START", startPoint.toString());
			

//			canvas.drawCircle(coordinatesStart.x, coordinatesStart.y, 4, blackPaint);
			canvas.drawBitmap(
					BitmapFactory.decodeResource(getResources(), R.drawable.start_pin_small), 
					coordinatesStart.x - 5, 
					coordinatesStart.y - 5, 
					blackPaint);
			
			Log.d("COORD", coordinatesStart.toString());
			
			

//			canvas.drawCircle(coordinatesGoal.x, coordinatesGoal.y, 4, blackPaint);
			canvas.drawBitmap(
					BitmapFactory.decodeResource(getResources(), R.drawable.finish_ping_small), 
					coordinatesGoal.x - 5, 
					coordinatesGoal.y - 5, 
					blackPaint);

			Log.d("START", endPoint.toString());
			Log.d("COORD", coordinatesGoal.toString());
			
			 
			 

		}
	}

}
