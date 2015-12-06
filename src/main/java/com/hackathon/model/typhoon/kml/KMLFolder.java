package com.hackathon.model.typhoon.kml;

import java.util.ArrayList;
import java.util.List;

public class KMLFolder extends AbstractKMLEntity {

	private List<KMLEntity> entites;
	
	public KMLFolder() {
		entites = new ArrayList<KMLEntity>();
	}

	@Override
	public Boolean isFolder() {
		return Boolean.TRUE;
	}
	
	@Override
	public KMLFolder asFolder() {
		return this;
	}
	
	@Override
	public void addKMLEntity(KMLEntity entity) {
		entites.add(entity);
	}
	
	@Override
	public List<KMLEntity> getKMLEntities() {
		return entites;
	}
	
	@Override
	public void dump() {
		System.out.println("---------- KMLFolder -----------");
		System.out.println("name:" + super.getName());
		System.out.println("description:" + super.getDescription());
		
		for(KMLEntity entity : entites) {
			entity.dump();
		}
	}
}
