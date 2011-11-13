package com.xmedic.troll.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.xmedic.troll.HomeScreenActiity;
import com.xmedic.troll.LevelPreviewActivity;
import com.xmedic.troll.R;

public class FailDialog  extends Dialog implements android.view.View.OnClickListener {

	 private final Context context;
	 private final View layout;
	 
	 private String levelId;
	 
	public FailDialog(Context context, String levelId) {
		super(context);
       this.context = context;
       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       layout = inflater.inflate(R.layout.faildialog,null);
       setContentView(layout);
       this.levelId = levelId;
       initComponents();
	}
	
	private void initComponents() {
		Button mainMenuButton = (Button)findViewById(R.id.mainMenu);
		mainMenuButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(context, HomeScreenActiity.class);
				context.startActivity(intent);		
			}
		});		

		Button retryLevel = (Button)findViewById(R.id.retryLevel);
		retryLevel.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(View v) {
				Intent intent = new Intent(context, LevelPreviewActivity.class);
				intent.putExtra(HomeScreenActiity.LEVEL_ID, levelId);
				context.startActivity(intent);		
			}
		});
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
