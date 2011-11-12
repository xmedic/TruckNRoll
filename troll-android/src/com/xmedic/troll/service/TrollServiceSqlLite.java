package com.xmedic.troll.service;

import com.xmedic.troll.service.db.TrollSqlLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class TrollServiceSqlLite {


	 @SuppressWarnings("unused")
	private SQLiteDatabase db;
	 
	 @SuppressWarnings("unused")
	private Context context;
	

	 public TrollServiceSqlLite(Context context) {
		 TrollSqlLiteOpenHelper helper = new TrollSqlLiteOpenHelper(context);
		 this.db = helper.getReadableDatabase();
		 this.context = context;
	 }

}
