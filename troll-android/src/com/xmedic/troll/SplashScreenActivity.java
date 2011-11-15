/**
 * 
 */
package com.xmedic.troll;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;

import com.xmedic.troll.service.db.TrollServiceSqlLite;

/**
 * @author vincentas
 *
 */
public class SplashScreenActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
                
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				startActivity(new Intent(SplashScreenActivity.this, HomeScreenActiity.class));				
			}
		}, 3000);
        
        findViewById(R.id.yo).setOnClickListener(new View.OnClickListener() {			
			public void onClick(final View v) {
				timer.cancel();
				startActivity(new Intent(SplashScreenActivity.this, HomeScreenActiity.class));
			}
		});
                
        Display display = getWindowManager().getDefaultDisplay(); 
        int width = display.getWidth();
        View truckImage = findViewById(R.id.movingtruck);
        TranslateAnimation animation = new TranslateAnimation(0, -width, 0, 0);
        animation.setFillAfter(true);
        animation.setDuration(3000);
        animation.setInterpolator(new AccelerateInterpolator());
        truckImage.startAnimation(animation);

        new TrollServiceSqlLite(this).getCity("1");
    }
}
