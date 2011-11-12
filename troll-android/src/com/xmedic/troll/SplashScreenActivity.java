/**
 * 
 */
package com.xmedic.troll;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

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
        LinearLayout ll = (LinearLayout) findViewById(R.id.yo);
        
        findViewById(R.id.xmediclogo).setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				startActivity(new Intent(SplashScreenActivity.this, HomeScreenActiity.class));
			}
		});
    }
}
