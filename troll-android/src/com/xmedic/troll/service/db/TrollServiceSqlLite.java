package com.xmedic.troll.service.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.xmedic.troll.service.TrollService;
import com.xmedic.troll.service.model.City;
import com.xmedic.troll.service.model.Level;

public class TrollServiceSqlLite implements TrollService {

	@SuppressWarnings("unused")
	private SQLiteDatabase db;
	 
	 @SuppressWarnings("unused")
	private Context context;
	

	 public TrollServiceSqlLite(Context context) {
		 SqlLiteOpenHelper helper = new SqlLiteOpenHelper(context);
		 this.db = helper.getReadableDatabase();
		 this.context = context;
	 }


//	public List<City> getCities() {
//		String query = "SELECT c.* FROM city c WHERE c.country = ?";
//		Cursor cursor = db.rawQuery(query, new String[] {"LT"} );
//		return DataTransfomer.toList(cursor, DoCity.instance);
//    }


	public List<Level> getLevels() {
		return new ArrayList<Level>();
	}


	public Level getLevel(String levelId) {
		return new Level();
	}


	public List<City> getNearbyCities(String cityId) {
		return new ArrayList<City>();
	}
}
