package com.xmedic.troll;

import java.util.List;

import com.xmedic.troll.components.ScrollableImageView;
import com.xmedic.troll.service.TrollService;
import com.xmedic.troll.service.db.TrollServiceSqlLite;
import com.xmedic.troll.service.model.City;
import com.xmedic.troll.service.model.Level;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TrucknrollAndroidActivity extends Activity {

	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private ScrollableImageView mapView;
	private TextView goalView;
	
	private Level level;
	private TrollService service;
	
	private View.OnClickListener citySelectedListener;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        service = new TrollServiceSqlLite(getBaseContext());
        
        loadComponents();
        initGraphics();
        level = service.getLevel("0");//getIntent().getExtras().getString(HomeScreenActiity.LEVEL_ID)
        moveToCity(service.getCity(level.getStartCityId()));
        
        City goal = service.getCity(level.getGoalCityId());
        goalView.setText("Goal: " + goal.getName());
        

    }

	private void moveToCity(City city) {
		
		List<City> nearestCities = service.getNearbyCities(city.getId(), level.getGoalCityId());
		
		Log.d("moveToCity", city.getId());
		Log.d("moveToCity", "neares city size " + nearestCities.size() + " id used " + city.getId());
		int index = 0;
		
		button1.setVisibility(View.INVISIBLE);
		button2.setVisibility(View.INVISIBLE);
		button3.setVisibility(View.INVISIBLE);
		button4.setVisibility(View.INVISIBLE);
		
		for(City nearestCity : nearestCities) {
			setChoice(nearestCity, index);	
			index++;
		}
		
		mapView.setCenter(city, this);
		mapView.setNearest(nearestCities);
		
		if(city.getId() == level.getGoalCityId()) {
			Toast toast = Toast.makeText(getApplicationContext(), 
					"Congrats! You have reached your destination", Toast.LENGTH_LONG);
			toast.show();
		}
	}

	private void setChoice(City city, int index) {
		Log.d("setChoice","Setting city" + city.getName() + " index " + index);
		Button buttonToUse = null;
		if(index == 0) {
			buttonToUse =  button3;
		} else  if(index == 1) {
			buttonToUse = button4;
		} else   if(index == 2) {
			buttonToUse = button1;
		} else  if(index == 3) {
			buttonToUse = button2;
		} 
		
		if(buttonToUse != null) {
			buttonToUse.setText(city.getName());
			buttonToUse.setTag(city.getId());
			buttonToUse.setVisibility(View.VISIBLE);
		}
	}
	
	private void citySelected(String cityId) {
		moveToCity(service.getCity(cityId));
		
	}

	private void loadComponents() {
	
		citySelectedListener = new OnClickListener() {
			
			public void onClick(View v) {
				citySelected(v.getTag().toString());
			}
		};
		
       button1 = (Button)findViewById(R.id.button1);
       button2 = (Button)findViewById(R.id.button2);
       button3 = (Button)findViewById(R.id.button3);
       button4 = (Button)findViewById(R.id.button4);
       button1.setOnClickListener(citySelectedListener);
       button2.setOnClickListener(citySelectedListener);
       button3.setOnClickListener(citySelectedListener);
       button4.setOnClickListener(citySelectedListener);
       
       mapView = (ScrollableImageView)findViewById(R.id.map);
       
       goalView = (TextView)findViewById(R.id.targetcity);
	}

	private void initGraphics() {
        Display d = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mapView.setScreenSize(d.getWidth(), d.getHeight());
	}
}