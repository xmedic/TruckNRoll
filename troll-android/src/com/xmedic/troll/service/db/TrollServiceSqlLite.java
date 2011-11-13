package com.xmedic.troll.service.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
        		"SELECT id, name, description, startCityId, goalCityId, timeLimit FROM level", 
        		new String[] {});
        List<Level> levels = DataTransfomer.toList(cursor, DoLevel.instance);        
        return levels;
	}

	public Level getLevel(String levelId) {
        Cursor cursor = db.rawQuery(
        		"SELECT id, name, description, startCityId, goalCityId, timeLimit " +
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

	public Set<City> getAllNeighbours(String cityId) {
        Cursor cursor = db.rawQuery(
        		"SELECT c.id, c.name, c.country, c.latitude, c.longitude, c.population, c.x, c.y " +
        				"FROM city c INNER JOIN road r ON c.id = r.toCityId " +
        				"WHERE r.fromCityId = ?", 
        		new String[] {cityId});
        return new HashSet<City> (DataTransfomer.toList(cursor, DoCity.instance));
	}

	
	public List<City> getNearbyCities(String cityId, String goalId) {
        Set<City> nearby = getAllNeighbours(cityId);
		return randomize(minimize(nearby, cityId, goalId));
	}

	private List<City> randomize(List<City> nearby) {
		List<City> temp = new ArrayList<City>();
		Date date = new Date();
		Random random = new Random(date.getTime());
		while (nearby.size() != 0) {
			City city = nearby.get((int) Math.round(random.nextInt(nearby.size())));
			temp.add(city);
			nearby.remove(city);
		}
		return temp;
	}

	private List<City> minimize(Set<City> nearby, String startId, String goalId) {
		if (nearby.size() > MAX_CHOICES) {
			String nextHop = getNextHopFromShortestPath(startId, goalId);
			List<City> result = new ArrayList<City>();
			for (City city : nearby) {
				if (city.getId().equals(goalId) || city.getId().equals(nextHop)) {
					result.add(city);
					break;
				}
			}
			if (result.size() > 0) {
				nearby.remove(result.get(0));
			} 
			for(City city : nearby) {
				result.add(city);
				if (result.size() == MAX_CHOICES) break;
			}
			return result;
		} else
			return new ArrayList<City>(nearby);
	}

	private String getNextHopFromShortestPath(String startId, String goalId) {
		List<String> shortestPath = getShortestPath(startId, goalId);
		return shortestPath.get(0);
	}
	
	public List<String> getShortestPath(String startCityId, String goalCityId) {
		Map<String, String> paths = new HashMap<String, String>();
		Set<String> needsVisit = new HashSet<String>();
		needsVisit.add(startCityId);
		boolean goalAchieved = false;
		
		while (needsVisit.size() > 0 && !goalAchieved) {
			Set<String> newNeedsVisit = new HashSet<String>();
			for (String city : needsVisit) {
				Set<City> neighbours = getAllNeighbours(city);
				for (City neighbour : neighbours) {
					if (!paths.containsKey(neighbour.getId())) {
						newNeedsVisit.add(neighbour.getId());
						paths.put(neighbour.getId(), city);
						if (goalCityId.equals(neighbour.getId())) {
							goalAchieved = true;
							break ;
						}
					}
				}
			}
			needsVisit = newNeedsVisit;
		}
		
		// build shortestPath from paths hashmap
		List<String> shortestPath = new ArrayList<String>();
		String hop = goalCityId;
		while (hop != startCityId) {
			shortestPath.add(hop);
			hop = paths.get(hop);
		}
		
		Collections.reverse(shortestPath);
		return shortestPath;
	}

}
