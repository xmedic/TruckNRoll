package com.xmedic.troll.service.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xmedic.troll.service.TrollService;
import com.xmedic.troll.service.db.DataTransfomer.DoCity;
import com.xmedic.troll.service.db.DataTransfomer.DoLevel;
import com.xmedic.troll.service.model.City;
import com.xmedic.troll.service.model.Level;

public class TrollServiceSqlLite implements TrollService {

	private static final int MAX_CHOICES = 4;

	private SQLiteDatabase db;
	 
	@SuppressWarnings("unused")
	private Context context;
	

	 public TrollServiceSqlLite(Context context) {
		 SqlLiteOpenHelper helper = new SqlLiteOpenHelper(context);
		 this.db = helper.getReadableDatabase();
		 this.context = context;
	 }

	public List<Level> getLevels() {
        Cursor cursor = db.rawQuery(
        		"SELECT id, name, description, startCityId, goalCityId FROM level", 
        		new String[] {});
        List<Level> levels = DataTransfomer.toList(cursor, DoLevel.instance);        
        return levels;
	}

	public Level getLevel(String levelId) {
        Cursor cursor = db.rawQuery(
        		"SELECT id, name, description, startCityId, goalCityId " +
        		"FROM level WHERE id = ?", 
        		new String[] {levelId});
        return DataTransfomer.to(cursor, DoLevel.instance);
	}

	public City getCity(String cityId) {
        Cursor cursor = db.rawQuery(
        		"SELECT id, name, country, latitude, longitude, population, x, y " +
        		"FROM city WHERE id = ?", 
        		new String[] {cityId});
        return DataTransfomer.to(cursor, DoCity.instance);
	}

	public List<City> getNearbyCities(String cityId, String goalId) {
        Cursor cursor = db.rawQuery(
        		"SELECT c.id, c.name, c.country, c.latitude, c.longitude, c.population, c.x, c.y " +
        				"FROM city c INNER JOIN road r ON c.id = r.toCityId " +
        				"WHERE r.fromCityId = ?", 
        		new String[] {cityId});

        List<City> nearby = DataTransfomer.toList(cursor, DoCity.instance);
		return randomize(minimize(nearby, goalId));
	}

	private List<City> randomize(List<City> nearby) {
		List<City> temp = new ArrayList<City>();
		while (nearby.size() != 0) {
			City city = nearby.get((int) Math.round(Math.random() * (nearby.size()-1)));
			temp.add(city);
			nearby.remove(city);
		}
		return temp;
	}

	private List<City> minimize(List<City> nearby, String goalId) {
		if (nearby.size() > MAX_CHOICES) {
			List<City> result = new ArrayList<City>();
			for (City city : nearby) {
				if (city.getId().equals(goalId)) {
					result.add(city);
					break;
				}
			}
			if (result.size() > 0)
				nearby.remove(result.get(0));
			result.addAll(nearby.subList(0, MAX_CHOICES - result.size()));
			return result;
		} else
			return nearby;
	}
}
