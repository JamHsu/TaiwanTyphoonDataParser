package com.hackathon.model.typhoon.kml;

import java.util.List;

public interface KMLEntity {
	public String getName();
	public String getDescription();
	public Boolean isFolder();
	public KMLFolder asFolder();
	public Boolean isPlacemark();
	public Placemark asPlacemark();
	public void addKMLEntity(KMLEntity entity);
	public List<KMLEntity> getKMLEntities();
	
	public void dump();
}
