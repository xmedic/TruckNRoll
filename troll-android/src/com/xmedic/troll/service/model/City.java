package com.xmedic.troll.service.model;
import android.graphics.Point;


public class City {

	
	private String id;
	
	private String name;
	
	private String country;
	
	private Double latitude;
	
	private Double longitude;
	
	private Point point;
	
	private Long population;
	

	public City() {
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(final Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(final Double longitude) {
		this.longitude = longitude;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(final Point point) {
		this.point = point;
	}

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(final Long population) {
		this.population = population;
	}
	
	@Override
	public boolean equals(final Object object) {
		if (null == object) {
            return false;
        }
		if (! (object instanceof City)) {
            return false;
        }
		
		City city = (City) object;
		return this.id.equals(city.getId());
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
}
