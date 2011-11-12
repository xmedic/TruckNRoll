/**
 * 
 */
package com.xmedic.troll;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author vincentas
 *
 */
public class SplashScreenActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.splashscreen);
                
        findViewById(R.id.yo).setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				startActivity(new Intent(SplashScreenActivity.this, HomeScreenActiity.class));
			}
		});
        
        new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				startActivity(new Intent(SplashScreenActivity.this, HomeScreenActiity.class));				
			}
		}, 3000);
    }
}
