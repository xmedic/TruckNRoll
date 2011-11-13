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
        
        setLevel(R.id.buttonLevel1, "1");
        setLevel(R.id.buttonLevel2, "2");
        setLevel(R.id.buttonLevel3, "3");
        setLevel(R.id.buttonLevel4, "4");
        setLevel(R.id.buttonLevel5, "5");        
	}

	private void setLevel(int buttonId, final String level) {
		Button button = (Button) findViewById(buttonId);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(HomeScreenActiity.this, LevelPreviewActivity.class);
				intent.putExtra(LEVEL_ID, level);
				startActivity(intent);
				
			}
		});
	}

	
}
