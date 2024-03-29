package com.xmedic.troll;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xmedic.troll.components.PreviewImage;
import com.xmedic.troll.service.TrollService;
import com.xmedic.troll.service.db.TrollServiceSqlLite;
import com.xmedic.troll.service.model.Level;

public class LevelPreviewActivity extends Activity {

	private TrollService service;
	
	
	@Override
	protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelpreview);
        
        View button = findViewById(R.id.clicktocontinue);

        
        service = new TrollServiceSqlLite(getBaseContext());
        
        final Level level = service.getLevel(getIntent().getExtras().getString(HomeScreenActiity.LEVEL_ID));
        PreviewImage previewMap = (PreviewImage)findViewById(R.id.previewMap);
        previewMap.setStartPoint(service.getCity(level.getStartCityId()).getPoint());
        previewMap.setEndPoint(service.getCity(level.getGoalCityId()).getPoint());
        
        loadTextLabels(level);

        button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(final View v) {
				Intent intent = new Intent(LevelPreviewActivity.this, TrucknrollAndroidActivity.class);
				intent.putExtra(HomeScreenActiity.LEVEL_ID, level.getId());
				startActivity(intent);
			}
		});
        
        
	}


	private void loadTextLabels(final Level level) {
        TextView goalText = (TextView)findViewById(R.id.goalText);
        TextView timeGivenText = (TextView)findViewById(R.id.timeText);
        TextView levelName = (TextView)findViewById(R.id.levelName);
        goalText.setText(String.format("You must go from city %s to city %s", service.getCity(level.getStartCityId()).getName(),
        		 service.getCity(level.getGoalCityId()).getName()));
        timeGivenText.setText(String.format("in %s seconds", level.getTimeLimit()));
        levelName.setText(level.getDescription());
        
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/frutiger.ttf");
        goalText.setTypeface(tf);
        timeGivenText.setTypeface(tf);
        levelName.setTypeface(tf);
        ((TextView)findViewById(R.id.itinerarytextView1)).setTypeface(tf);
        ((TextView)findViewById(R.id.taptextView2)).setTypeface(tf);
	}
}
