package com.hackathon.util;

public class MapCalculateUtil {
	
	public static double calculateAngle(double lat1, double lng1, double lat2, double lng2) {
		double dLon = (lng2 - lng1);

        double y = Math.sin(dLon) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1)
                * Math.cos(lat2) * Math.cos(dLon);

        double brng = Math.atan2(y, x);

        brng = Math.toDegrees(brng);
        brng = (brng + 360) % 360;
        
        return brng;
	}
}
