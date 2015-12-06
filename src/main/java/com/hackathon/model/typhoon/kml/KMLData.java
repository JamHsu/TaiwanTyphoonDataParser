package com.hackathon.model.typhoon.kml;

import java.util.ArrayList;
import java.util.List;

public class KMLData {
	private List<KMLFolder> kmlFolders;
	
	public KMLData() {
		kmlFolders = new ArrayList<KMLFolder>();
	}
	
	public void addKMLFolder(KMLFolder folder) {
		kmlFolders.add(folder);
	}
	
	public List<KMLFolder> getKMLFolders() {
		return kmlFolders;
	}
	
	
}
