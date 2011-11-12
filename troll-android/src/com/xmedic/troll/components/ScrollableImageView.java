package com.xmedic.troll.components;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.xmedic.troll.service.MapMath;
import com.xmedic.troll.service.model.City;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class ScrollableImageView extends ImageView {

	float downX, downY;
    int totalX, totalY;
    int scrollByX, scrollByY;
    int maxLeft;
    int maxRight;
    int maxTop;
    int maxBottom;
    
    int maxX;
    int maxY;
    
    Paint blackPaint;
    Paint bluePaint;
    Paint linePaint;
    int canvasOffsetX;
    int canvasOffsetY;

    private City city;
    private List<City> nearestCities;
	private int screenHeight;
	private int screenWidth;
	
	List<Point> history = new ArrayList<Point>();
    
    public ScrollableImageView(Context context, AttributeSet set) {
    	super(context, set);
    	setupLines();
    }
    
    
    
	public ScrollableImageView(Context context) {
		super(context);
		setupLines();
	}
	
	private void setupLines() {
		 blackPaint = new Paint();
		 blackPaint.setColor(Color.BLACK);
		 blackPaint.setTextSize(30);
		 
		 bluePaint = new Paint();
		 bluePaint.setColor(Color.BLUE);
		 
		 linePaint = new Paint();
		 linePaint.setColor(Color.RED);
		 linePaint.setStrokeWidth(5);
	}



	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		if(city != null) {
			Point coordinates = MapMath.toDrawPoint(city.getPoint(), maxX, maxY);
			canvas.drawCircle(coordinates.x, coordinates.y, 10, blackPaint);
			canvas.drawText(city.getName(), coordinates.x - 20, coordinates.y - 10, blackPaint);
		}
		if(nearestCities != null) {
			for(City nearestCity : nearestCities) {
				Point coordinates = MapMath.toDrawPoint(nearestCity.getPoint(), maxX, maxY);
				canvas.drawCircle(coordinates.x, coordinates.y, 10, bluePaint);
			}
		}
		if(history.size() > 1) {
			Point previous = null;
			for(Point point : history) {
				if(previous != null) {
					Log.d("DRAW", "Prev x" +previous.x + " Prev y " + previous.y + " Px + "+point.x + " Py " + point.y);
					canvas.drawLine(previous.x, previous.y, point.x, point.y, linePaint);
				}
				previous = point;
			}
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		 float currentX = 0, currentY = 0;
		 
         switch (event.getAction())
         {
             case MotionEvent.ACTION_DOWN:
                 downX = event.getX();
                 downY = event.getY();
                 break;

             case MotionEvent.ACTION_MOVE:
                 currentX = event.getX();
                 currentY = event.getY();
                 scrollByX = (int)(downX - currentX);
                 scrollByY = (int)(downY - currentY);

                 // scrolling to left side of image (pic moving to the right)
                 if (currentX > downX)
                 {
                     if (totalX == maxLeft)
                     {
                         scrollByX = 0;
                     }
                     if (totalX > maxLeft)
                     {
                         totalX = totalX + scrollByX;
                     }
                     if (totalX < maxLeft)
                     {
                         scrollByX = maxLeft - (totalX - scrollByX);
                         totalX = maxLeft;
                     }
                 }

                 // scrolling to right side of image (pic moving to the left)
                 if (currentX < downX)
                 {
                     if (totalX == maxRight)
                     {
                         scrollByX = 0;
                     }
                     if (totalX < maxRight)
                     {
                         totalX = totalX + scrollByX;
                     }
                     if (totalX > maxRight)
                     {
                         scrollByX = maxRight - (totalX - scrollByX);
                         totalX = maxRight;
                     }
                 }

                 // scrolling to top of image (pic moving to the bottom)
                 if (currentY > downY)
                 {
                     if (totalY == maxTop)
                     {
                         scrollByY = 0;
                     }
                     if (totalY > maxTop)
                     {
                         totalY = totalY + scrollByY;
                     }
                     if (totalY < maxTop)
                     {
                         scrollByY = maxTop - (totalY - scrollByY);
                         totalY = maxTop;
                     }
                 }

                 // scrolling to bottom of image (pic moving to the top)
                 if (currentY < downY)
                 {
                     if (totalY == maxBottom)
                     {
                         scrollByY = 0;
                     }
                     if (totalY < maxBottom)
                     {
                         totalY = totalY + scrollByY;
                     }
                     if (totalY > maxBottom)
                     {
                         scrollByY = maxBottom - (totalY - scrollByY);
                         totalY = maxBottom;
                     }
                 }

                 scrollBy(scrollByX, scrollByY);
                 downX = currentX;
                 downY = currentY;
                 break;

                 
         }
         return true;
	}

	public void setCurrent(int x, int y) {
		totalX = x;
		totalY = y;
		
	}

	public void setScreenSize(int width, int height) {
		this.screenWidth = width;
		this.screenHeight = height;
		maxX = (int) ((MapMath.MAP_WIDTH / 2) - (width / 2));
		maxY = (int) ((MapMath.MAP_HEIGHT / 2) - (height / 2));

		// set scroll limits
		maxLeft = (maxX * -1);
		maxRight = maxX;
		maxTop = (maxY * -1);
		maxBottom = maxY;
		
		
	}

	public void moveTo(Point point) {
		scrollTo(point.x, point.y);
		setCurrent(point.x, point.y);
	}

	public void setCenter(City city) {
		history.add(MapMath.toDrawPoint(city.getPoint(), maxX, maxY));
		moveTo(city.getExternalPoint());
		this.city = city;
	}

	public void setNearest(List<City> nearestCities) {
		this.nearestCities = nearestCities;
	}
}
