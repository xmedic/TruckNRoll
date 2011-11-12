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
import android.widget.Button;

/**
 * @author vincentas
 *
 */
public class HomeScreenActiity extends Activity {

	public static final String LEVEL_ID = "levelId";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.homescreen);
        
        Button buttonLevel4 = (Button) findViewById(R.id.buttonLevel4);
        buttonLevel4.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(HomeScreenActiity.this, TrucknrollAndroidActivity.class);
				intent.putExtra(LEVEL_ID, "1");
				startActivity(intent);
				
			}
		});
        
	}

	
}
