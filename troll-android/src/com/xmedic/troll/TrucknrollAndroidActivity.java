package com.xmedic.troll;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.xmedic.troll.components.CountDown;
import com.xmedic.troll.components.GameMapView;
import com.xmedic.troll.dialogs.FailDialog;
import com.xmedic.troll.dialogs.SuccessDialog;
import com.xmedic.troll.service.TrollService;
import com.xmedic.troll.service.db.TrollServiceSqlLite;
import com.xmedic.troll.service.model.City;
import com.xmedic.troll.service.model.Level;


public class TrucknrollAndroidActivity extends Activity {

	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private GameMapView mapView;
	private TextView goalView;
	private TextView timeLeftView;
	
	private Level level;
	private TrollService service;
	
	private View.OnClickListener citySelectedListener;
	private CountDown counter;
	
	private SuccessDialog successDialog;
	private FailDialog failDialog;
	
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        service = new TrollServiceSqlLite(getBaseContext());
        
        String levelId = getIntent().getExtras().getString(HomeScreenActiity.LEVEL_ID);
        level = service.getLevel(levelId);
        
        loadComponents();
        initGraphics();
        
        moveToCity(service.getCity(level.getStartCityId()));
        
        City goal = service.getCity(level.getGoalCityId());
        goalView.setText(goal.getName());
        mapView.setGoalCity(goal);

        counter = new CountDown(Integer.parseInt(level.getTimeLimit()) * 1000,1000, timeLeftView);
        counter.start();
        counter.setOnFinishListener(new CountDown.OnCounterFinishListener() {	
			@Override
            public void finished() {
				failDialog.show();
				hideButtons();
			}
		});
    }

	private void moveToCity(final City city) {
		
		hideButtons();		
	
		int index = 0;
        mapView.setCenter(city);
		mapView.setNearest(null);
		
		if(city.getId().equals(level.getGoalCityId())) {
			counter.cancel();
			timeLeftView.setTextColor(Color.GREEN);
			successDialog.show();
			return;
		}
		
		List<City> nearestCities = service.getNearbyCities(city.getId(), level.getGoalCityId());
		for(City nearestCity : nearestCities) {
			setChoice(nearestCity, index);	
			index++;
		}
		mapView.setNearest(nearestCities);
	}

	private void hideButtons() {
		button1.setVisibility(View.INVISIBLE);
		button2.setVisibility(View.INVISIBLE);
		button3.setVisibility(View.INVISIBLE);
		button4.setVisibility(View.INVISIBLE);
	}

	private void setChoice(final City city, final int index) {
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
	
	private void citySelected(final String cityId) {
		moveToCity(service.getCity(cityId));
		
	}

	private void loadComponents() {
	
		citySelectedListener = new OnClickListener() {
			
			public void onClick(final View v) {
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
       
       goalView = (TextView)findViewById(R.id.targetcity);
       mapView = (GameMapView)findViewById(R.id.map);
       timeLeftView = (TextView)findViewById(R.id.timeleftlabel);
       
       successDialog =  new SuccessDialog(this, level.getId()); 
       failDialog =  new FailDialog(this,level.getId());
       
       Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/frutiger.ttf");
       button1.setTypeface(tf);
       button2.setTypeface(tf);
       button3.setTypeface(tf);
       button4.setTypeface(tf);
       
       tf = Typeface.createFromAsset(getAssets(), "fonts/fixed.ttf");
       goalView.setTypeface(tf);
   	   timeLeftView.setTypeface(tf);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if(counter != null) {
			counter.cancel();
		}
	}

	private void initGraphics() {
        Display d = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mapView.setScreenSize(d.getWidth(), d.getHeight());
	}
}
