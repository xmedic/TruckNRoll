package com.xmedic.troll;

import com.xmedic.troll.components.ScrollableImageView;
import com.xmedic.troll.service.TrollService;
import com.xmedic.troll.service.db.TrollServiceSqlLite;
import com.xmedic.troll.service.model.Level;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.xmedic.troll.components.ScrollableImageView;

public class TrucknrollAndroidActivity extends Activity {

	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private ScrollableImageView mapView;
	
	private Level level;
	private TrollService service;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGraphics();
        loadComponents();

        Display d = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mapView.setScreenSize(d.getWidth(), d.getHeight());
        
//        level = service.getLevel(getIntent().getExtras().getString(HomeScreenActiity.LEVEL_ID));
       
        button4.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				mapView.moveTo(200, 200);				
			}
		});
    }

	private void loadComponents() {
       button1 = (Button)findViewById(R.id.button1);
       button2 = (Button)findViewById(R.id.button2);
       button3 = (Button)findViewById(R.id.button3);
       button4 = (Button)findViewById(R.id.button4);
       
       
       
       mapView = (ScrollableImageView)findViewById(R.id.map);
	}

	private void initGraphics() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        
        
	}

}