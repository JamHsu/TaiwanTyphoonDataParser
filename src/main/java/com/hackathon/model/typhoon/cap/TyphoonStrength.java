package com.hackathon.model.typhoon.cap;

public enum TyphoonStrength {
	
	Light(1, "輕"),
	Medium(2, "中"),
	Strong(3, "強");
	
	private int id;
	private String chtName;
	
	TyphoonStrength(int id, String chtName) {
		this.id = id;
		this.chtName = chtName;
	}
	
	public int getId() {
		return id;
	}
	
	public String getChtName() {
		return chtName;
	}
	
	public static TyphoonStrength getTyphoonStrengthByChtName(String chtName) {
		for(TyphoonStrength strength : TyphoonStrength.values()) {
			if(strength.getChtName().equals(chtName)) {
				return strength;
			}
		}
		throw new IllegalArgumentException("Unknown name:" + chtName);
	}
}
