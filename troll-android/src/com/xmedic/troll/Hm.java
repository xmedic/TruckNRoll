/**
 * 
 */
package com.xmedic.troll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

/**
 * @author vincentas
 *
 */
public class Hm extends ImageView {

	public Hm(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		Paint p = new Paint();
		p.setColor(Color.GREEN);
		p.setStrokeWidth(20);
		canvas.drawLine(0, 0, 100, 100, p);
	}
	
	

	
}
