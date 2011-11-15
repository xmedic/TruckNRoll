package com.xmedic.troll.components;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;

import com.xmedic.troll.R;
import com.xmedic.troll.service.MapMath;
import com.xmedic.troll.service.MapMath.MapType;
import com.xmedic.troll.service.model.City;

public class GameMapView extends ScrollableImageView {


    Paint blackPaint;
    Paint labelPaint;
    Paint bluePaint;
    Paint linePaint;
    Paint goalPaint;
	Paint initialCityPaint;
    
    int canvasOffsetX;
    int canvasOffsetY;

    private City city;
    private City goalCity;
	private City initialCity;
	
    private List<City> nearestCities;

	List<Point> history = new ArrayList<Point>();

    
    public GameMapView(final Context context, final AttributeSet set) {
    	super(context, set);
    	setupLines();
    }
	
	private void setupLines() {
		 blackPaint = new Paint();
		 blackPaint.setColor(Color.BLACK);
		 blackPaint.setTextSize(30);


		 labelPaint = new TextPaint();
		 labelPaint.setColor(Color.BLACK);
		 labelPaint.setTextSize(16);
		 labelPaint.setAntiAlias(true);
		 labelPaint.setTypeface(Typeface.DEFAULT_BOLD);
		 
		 bluePaint = new Paint();
		 bluePaint.setColor(Color.BLUE);
		 
		 linePaint = new Paint();
		 linePaint.setColor(Color.GRAY);
		 linePaint.setStrokeWidth(5);
		 linePaint.setAntiAlias(true);
		 linePaint.setPathEffect(new DashPathEffect(new float[] {10, 10}, 0));
		 
		 goalPaint = new Paint();
		 goalPaint.setColor(Color.RED);
		 goalPaint.setStrokeWidth(5);

		 initialCityPaint  = new Paint();
		 initialCityPaint.setColor(Color.CYAN);
		 initialCityPaint.setStrokeWidth(5);
	}
	
	@Override
	protected void onDraw(final Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		if(history.size() > 1) {
			drawHistoryCurve(canvas);
		}

		if(city != null) {
			Point coordinates = MapMath.toDrawPoint(city.getPoint(), maxX, maxY);

			canvas.drawText(city.getName(), coordinates.x - 20, coordinates.y - 10, labelPaint);
			canvas.drawBitmap(
					BitmapFactory.decodeResource(getResources(), R.drawable.active_pin), 
					coordinates.x - 15, 
					coordinates.y - 12, 
					labelPaint);

		}
		if(nearestCities != null) {
			for(City nearestCity : nearestCities) {
				Point coordinates = MapMath.toDrawPoint(nearestCity.getPoint(), maxX, maxY);
				canvas.drawBitmap(
						BitmapFactory.decodeResource(getResources(), R.drawable.normal_pin), 
						coordinates.x - 15, 
						coordinates.y - 12, 
						labelPaint);
			}
		}
		if(goalCity != null) {
			Point coordinates = MapMath.toDrawPoint(goalCity.getPoint(), maxX, maxY);
			canvas.drawBitmap(
					BitmapFactory.decodeResource(getResources(), R.drawable.finish_ping), 
					coordinates.x - 15, 
					coordinates.y - 12, 
					labelPaint);
		}
		if(initialCity != null) {
			Point coordinates = MapMath.toDrawPoint(initialCity.getPoint(), maxX, maxY);
			canvas.drawBitmap(
					BitmapFactory.decodeResource(getResources(), R.drawable.active_pin), 
					coordinates.x - 15, 
					coordinates.y - 12, 
					labelPaint);
		}
	}



	private void drawHistoryCurve(final Canvas canvas) {
		Path path = new Path();

		for(int i = 0; i < history.size(); i++) {
			Point point = history.get(i);
			if (i == 0) {
				path.moveTo(point.x, point.y);
			} else { 
				path.lineTo(point.x, point.y);
			}
			if (i+1 == history.size()) {
				path.moveTo(point.x, point.y);
			}
		}
		path.close();

		Paint linePaint = new Paint();
		linePaint.setColor(Color.GRAY);
		linePaint.setStyle(Paint.Style.STROKE);
		linePaint.setStrokeWidth(5);
		linePaint.setAntiAlias(true);
		linePaint.setPathEffect(new DashPathEffect(new float[] {10, 10}, 0));

		canvas.drawPath(path, linePaint);
	}


    public void setCenter(final City city) {
		history.add(MapMath.toDrawPoint(city.getPoint(), maxX, maxY));

        moveTo(MapMath.toScrollPoint(city.getPoint(), MapType.NORMAL));

		if(this.city == null) {
			this.initialCity = city;
		}
		
		this.city = city;
	}

	public void setNearest(final List<City> nearestCities) {
		this.nearestCities = nearestCities;
	}

	public void setGoalCity(final City goalCity) {
		this.goalCity = goalCity;
	}
}
