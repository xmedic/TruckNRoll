package com.xmedic.troll.service.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqlLiteOpenHelper extends SQLiteOpenHelper {

	 private static final String LOG_TAG = "troll-db";

	 public static final String CITY = "city";
	 
	 private Context context;

	 
	 public SqlLiteOpenHelper(Context context) {
		    super(context, DDL.DB_NAME, null, DDL.DB_VERSION);
		    this.context = context;
	 }

	@Override
	public void onCreate(SQLiteDatabase db) {

		 Log.i(LOG_TAG, "Creating initial database");
		    try {
		    	db.execSQL(DDL.CITY);
		    } catch (Exception e) {
		    	Log.e(LOG_TAG, e.getMessage());
		    }
		    Log.i(LOG_TAG, "Finished creating database.");


		    Log.i(LOG_TAG, "Inserting initial data");
		    SqlLiteImportHelper.importInitialData(db, context);     
		    Log.i(LOG_TAG, "Finished inserting initial data");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(LOG_TAG, String.format(
				"Upgrading database from version %s to %s which will destroy all old data", 
				oldVersion, 
				newVersion));
		 db.execSQL("DROP TABLE IF EXISTS city;");
	     onCreate(db);
	}
	
	 private static class DDL {

         public static String DB_NAME = "trucknroll";
         
         public static int DB_VERSION = 1;

         public static String CITY = 
        		 "CREATE TABLE " + SqlLiteOpenHelper.CITY + " (" +
                         "id             TEXT PRIMARY KEY, " +
                         "name           TEXT NOT NULL, " +
                         "country        TEXT NOT NULL, " +
                         "latitude       REAL NOT NULL, " +
                         "longitude      REAL NOT NULL, " +
                         "population     INTEGER ";
	 }

}