package com.hackathon.model.typhoon.kml;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractKMLEntity implements KMLEntity {
	
	private String name;
	private String description;

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public List<KMLEntity> getKMLEntities() {
		return new ArrayList<KMLEntity>();
	}
	
	@Override
	public void addKMLEntity(KMLEntity entity) {}
	
	@Override
	public Boolean isFolder() {
		return Boolean.FALSE;
	}
	
	@Override
	public KMLFolder asFolder() {
		return null;
	}
	
	@Override
	public Boolean isPlacemark() {
		return Boolean.FALSE;
	}
	
	@Override
	public Placemark asPlacemark() {
		return null;	
	}

}
