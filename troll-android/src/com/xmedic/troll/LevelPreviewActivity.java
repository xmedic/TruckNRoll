package com.xmedic.troll;

import com.xmedic.troll.components.PreviewImage;
import com.xmedic.troll.service.TrollService;
import com.xmedic.troll.service.db.TrollServiceSqlLite;
import com.xmedic.troll.service.model.Level;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class LevelPreviewActivity extends Activity {

	private TrollService service;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.levelpreview);
        
        service = new TrollServiceSqlLite(getBaseContext());
        
        final Level level = service.getLevel(getIntent().getExtras().getString(HomeScreenActiity.LEVEL_ID));
        PreviewImage previewMap = (PreviewImage)findViewById(R.id.previewMap);
        previewMap.setStartPoint(service.getCity(level.getStartCityId()).getPoint());
        previewMap.setEndPoint(service.getCity(level.getGoalCityId()).getPoint());
        
        Button button = (Button)findViewById(R.id.startLevel);
        button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(LevelPreviewActivity.this, TrucknrollAndroidActivity.class);
				intent.putExtra(HomeScreenActiity.LEVEL_ID, level.getId());
				startActivity(intent);
			}
		});
        
        
	}
}
