package com.xmedic.troll.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.xmedic.troll.HomeScreenActiity;
import com.xmedic.troll.R;
import com.xmedic.troll.TrucknrollAndroidActivity;

public class FailDialog extends Dialog {

	 private final Context context;
	 private final View layout;
	 
	 private final String levelId;
	 
	public FailDialog(final Context context, final String levelId) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
       this.context = context;
       setTitle("No beer for today...");
       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       layout = inflater.inflate(R.layout.faildialog,null);
       setContentView(layout);
       this.levelId = levelId;
       initComponents();
	}
	
	private void initComponents() {
		Button mainMenuButton = (Button)findViewById(R.id.mainMenu);
		mainMenuButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(final View v) {
				Intent intent = new Intent(context, HomeScreenActiity.class);
				context.startActivity(intent);		
			}
		});		

		Button retryLevel = (Button)findViewById(R.id.retryLevel);
		retryLevel.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(final View v) {
				Intent intent = new Intent(context, TrucknrollAndroidActivity.class);
				intent.putExtra(HomeScreenActiity.LEVEL_ID, levelId);
				context.startActivity(intent);		
			}
		});
	}
}
