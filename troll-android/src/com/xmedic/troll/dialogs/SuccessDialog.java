package com.xmedic.troll.dialogs;

import com.xmedic.troll.HomeScreenActiity;
import com.xmedic.troll.LevelPreviewActivity;
import com.xmedic.troll.R;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class SuccessDialog extends Dialog implements android.view.View.OnClickListener {

	 private final Context context;
	 private final View layout;
	 
	 private String levelId;
	 
	public SuccessDialog(Context context, String levelId) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setTitle("Job well done.");
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.successdialog,null);
        setContentView(layout);
        this.levelId = levelId;
        initComponents();
	}

	private void initComponents() {
		Button mainMenuButton = (Button)findViewById(R.id.mainMenuSuccess);
		mainMenuButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(context, HomeScreenActiity.class);
				context.startActivity(intent);		
			}
		});
		
		Button reviewButton = (Button)findViewById(R.id.reviewLevel);
		reviewButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				hide();	
			}
		});
		

		Button nextLevelButton = (Button)findViewById(R.id.nextLevel);
		nextLevelButton.setOnClickListener(new View.OnClickListener() {
		Integer nextLevel =  (Integer.parseInt(levelId) + 1);
			public void onClick(View v) {
				Intent intent = new Intent(context, LevelPreviewActivity.class);
				intent.putExtra(HomeScreenActiity.LEVEL_ID, nextLevel.toString());
				context.startActivity(intent);		
			}
		});
	}
	public void onClick(View v) {
		
	}

}
