package com.xmedic.troll.service.db;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.database.Cursor;
import android.graphics.Point;

import com.xmedic.troll.service.model.City;
import com.xmedic.troll.service.model.Level;

public class DataTransfomer {
        
        public static <T> T to(Cursor cursor, Do<T> doe) {
                try {
                        T result = null;
                        
                        if (cursor.moveToFirst()) {
                                result = doe.get(cursor);
                        }
                        
                        return result;
                } finally {
                        closeCursor(cursor);
                }               
        }
        
        private static void closeCursor(Cursor cursor) {
                if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                }
        }
        
        public static <T> Set<T> toSet(Cursor cursor, Do<T> doe) {
                try {
                        Set<T> result = new HashSet<T>();
        
                        if (cursor.moveToFirst()) {
                                do {
                                        result.add(doe.get(cursor));
                                } while (cursor.moveToNext());
                        }
        
                        return result;
                } finally {
                        closeCursor(cursor);
                }
        }
        
        public static <T> List<T> toList(Cursor cursor, Do<T> doe) {
                try {
                        List<T> result = new ArrayList<T>();
        
                        if (cursor.moveToFirst()) {
                                do {
                                        result.add(doe.get(cursor));
                                } while (cursor.moveToNext());
                        }
                        
                        return result;
                } finally {
                        closeCursor(cursor);
                }               
        }
        
        interface Do<T> {               
                T get(Cursor cursor);
        }
        

        public static class DoCity implements Do<City> {
                
                public final static DoCity instance = new DoCity();
                
                public static String[] columns = new String[] {
                	"id", 
                	"name", 
                	"country", 
                	"latitude", 
                	"longitude", 
                	"population",
                	"x",
                	"y"};

				public City get(Cursor cursor) {
                    City city = new City();

                    city.setId(cursor.getString(0));
                    city.setName(cursor.getString(1));
                    city.setCountry(cursor.getString(2));
                    city.setLatitude(cursor.getDouble(3));
                    city.setLongitude(cursor.getDouble(4));
                    city.setPopulation(cursor.getLong(5));
                    
                    if (!cursor.isNull(6)) {
                    	Point point = new Point();
                    	point.x = cursor.getInt(6);
                    	point.y = cursor.getInt(7);
                    	city.setPoint(point);
                    }

                    return city;
				}
                
        }
        

        public static class DoLevel implements Do<Level> {
            
            public final static DoLevel instance = new DoLevel();
            
            public static String[] columns = new String[] {
            	"id", 
            	"name", 
            	"description", 
            	"startCityId",
            	"fromCityId",
            	"timeLimit"};

			public Level get(Cursor cursor) {
                Level level = new Level();

                level.setId(cursor.getString(0));
                level.setName(cursor.getString(1));
                level.setDescription(cursor.getString(2));
                level.setStartCityId(cursor.getString(3));
                level.setGoalCityId(cursor.getString(4));
                level.setTimeLimit(cursor.getString(5));
               
                return level;
			}
        }
}
