package com.xmedic.troll.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.xmedic.troll.R;

public class FailDialog  extends Dialog implements android.view.View.OnClickListener {

	 private final Context context;
	 private final View layout;
	 
	public FailDialog(Context context) {
		super(context);
       this.context = context;
       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       layout = inflater.inflate(R.layout.faildialog,null);
       setContentView(layout);
       
//       Button okButton = (Button)layout.findViewById(R.id.btnSubmitPub);
//       okButton.setOnClickListener(this);
//       Button cancelButton = (Button)layout.findViewById(R.id.btnSubmitPubCancel);
//       cancelButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
