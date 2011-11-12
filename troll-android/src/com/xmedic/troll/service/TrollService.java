package com.xmedic.troll.service;

import java.util.List;

import com.xmedic.troll.service.model.City;
import com.xmedic.troll.service.model.Level;

public interface TrollService {

	public List<Level> getLevels();
	
	public Level getLevel(String levelId);
	
	public List<City> getNearbyCities(String cityId);
	
	public City getCity(String id);

}
