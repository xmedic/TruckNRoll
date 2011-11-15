package com.xmedic.troll.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.xmedic.troll.HomeScreenActiity;
import com.xmedic.troll.LevelPreviewActivity;
import com.xmedic.troll.R;

public class SuccessDialog extends Dialog {

	 private final Context context;
	 private final View layout;
	 
	 private final String levelId;
	 
	public SuccessDialog(final Context context, final String levelId) {
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
			
			public void onClick(final View v) {
				Intent intent = new Intent(context, HomeScreenActiity.class);
				context.startActivity(intent);		
			}
		});
		
		Button reviewButton = (Button)findViewById(R.id.reviewLevel);
		reviewButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(final View v) {
				hide();	
			}
		});
		

		Button nextLevelButton = (Button)findViewById(R.id.nextLevel);
		nextLevelButton.setOnClickListener(new View.OnClickListener() {
		Integer nextLevel =  (Integer.parseInt(levelId) + 1);
			public void onClick(final View v) {
				Intent intent = new Intent(context, LevelPreviewActivity.class);
				intent.putExtra(HomeScreenActiity.LEVEL_ID, nextLevel.toString());
				context.startActivity(intent);		
			}
		});
	}
}
