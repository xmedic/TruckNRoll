package com.xmedic.troll.service.model;

public class Level {

	
	private String id;
	
	private String name;
	
	private String description;
	
	private String startCityId;
	
	private String goalCityId;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartCityId() {
		return startCityId;
	}

	public void setStartCityId(String startCityId) {
		this.startCityId = startCityId;
	}

	public String getGoalCityId() {
		return goalCityId;
	}

	public void setGoalCityId(String goalCityId) {
		this.goalCityId = goalCityId;
	}

}
