package com.xmedic.troll;

import com.xmedic.troll.components.ScrollableImageView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.Button;

public class TrucknrollAndroidActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main);
        
        Button button = (Button)findViewById(R.id.button1);
        final ScrollableImageView imageView = (ScrollableImageView)findViewById(R.id.map);
        button.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				imageView.scrollTo(-202, -462);
				imageView.setCurrent(-202, -462);
				
			}
		});
        
    }
}