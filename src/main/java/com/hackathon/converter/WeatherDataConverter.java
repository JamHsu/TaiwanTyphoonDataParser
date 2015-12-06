package com.hackathon.converter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.hackathon.model.typhoon.cap.TyphoonInfo;
import com.hackathon.model.typhoon.kml.Coordinates;
import com.hackathon.model.typhoon.kml.ForecastType;
import com.hackathon.model.typhoon.kml.KMLData;
import com.hackathon.model.typhoon.kml.KMLEntity;
import com.hackathon.model.typhoon.kml.KMLFolder;
import com.hackathon.model.typhoon.kml.Placemark;
import com.hackathon.util.DateUtil;
import com.hacker.bean.request.Location;
import com.hacker.bean.request.TyphoonCWBBean;
import com.hacker.bean.request.TyphoonCoordinationBean;
import com.hacker.bean.request.TyphoonForecastLocationBean;
import com.hacker.bean.request.TyphoonInfoBean;
import com.hacker.bean.request.TyphoonNewsBean;

public class WeatherDataConverter {
	
	private static final Logger logger = Logger.getLogger(WeatherDataConverter.class);
	private List<TyphoonCoordinationBean> typhoonCoordinates = new ArrayList<TyphoonCoordinationBean>();
	private List<TyphoonForecastLocationBean> typhoonForecasts = new ArrayList<TyphoonForecastLocationBean>();
	
	public TyphoonCWBBean convertToBean(TyphoonInfo typhoonInfo, KMLData kmlData) throws ParseException {
		String chtName = typhoonInfo.getChtName();
		String engName = typhoonInfo.getEngName();
		
		for(KMLFolder kmlFolder : kmlData.getKMLFolders()) {
			logger.debug("Convert KMLFolder to DataBean:" + kmlFolder.getName());
			processKMLFolderToBean(kmlFolder);
		}
		
		TyphoonNewsBean newsBean = new TyphoonNewsBean(
				typhoonInfo.getStrength().getId(), typhoonInfo.getAirpressure(), typhoonInfo.getTyphoonRotation().intValue(),
				typhoonInfo.getTyphoonSpeed(), typhoonInfo.getDescription(), typhoonInfo.getAlertNumber(),
				typhoonInfo.getCenterLocation().getLongitude(), typhoonInfo.getCenterLocation().getLatitude(),
				typhoonInfo.getTyphoonCenterDistance());
		TyphoonInfoBean infoBean = new TyphoonInfoBean(chtName, engName, typhoonInfo.getTyphoonNumber(), "Unknown", typhoonInfo.getDescription());
		TyphoonCWBBean CWBBean = new TyphoonCWBBean(infoBean, newsBean, typhoonCoordinates, typhoonForecasts);
		return CWBBean;
	}

	private void processKMLFolderToBean(KMLFolder kmlFolder)
			throws ParseException {
		for(KMLEntity kmlEntity : kmlFolder.getKMLEntities()) {
			if(kmlEntity.isFolder()) {
				processKMLFolderToBean(kmlEntity.asFolder());
			} else if(kmlEntity.isPlacemark()) {
				Placemark placemark = kmlEntity.asPlacemark();
				ForecastType forecastType = ForecastType.getForecastTypeByChtName(placemark.getName());
				if(ForecastType.Unknown.equals(forecastType)) {
					logger.debug("weather data skip convert " + placemark.getName());
					continue;
				} else if(ForecastType.Typhoon_location_forecast.equals(forecastType)) {
					
					logger.debug("convert " + forecastType.toString() + " to TyphoonForecastLocationBean");
					TyphoonForecastLocationBean forecastLocBean = convertToForecastLocBean(placemark);
					typhoonForecasts.add(forecastLocBean);
					
				} else {
					
					logger.debug("convert " + forecastType.toString() + " to TyphoonCoordinationBean");
					List<Coordinates> coordinates = placemark.getCoordinates();
					TyphoonCoordinationBean coordBean = convertToTyphoonCoordinationBean(forecastType, coordinates);
					typhoonCoordinates.add(coordBean);
				}
				
			}
		}
	}

	@SuppressWarnings("deprecation")
	private TyphoonForecastLocationBean convertToForecastLocBean(
			Placemark placemark) throws ParseException {
		Location location = new Location(
				placemark.getCoordinates().get(0).getLongitude(),
				placemark.getCoordinates().get(0).getLatitude());
		Date forecasttime = DateUtil.typhoonDateToDate(placemark.getName());
		// workaround to get year
		forecasttime.setYear(new Date().getYear());
		TyphoonForecastLocationBean forecastLocBean = new TyphoonForecastLocationBean(forecasttime, location, placemark.getDescription());
		return forecastLocBean;
	}
	
	private TyphoonCoordinationBean convertToTyphoonCoordinationBean(ForecastType type, List<Coordinates> coordinates) {
		List<Location> locations = new ArrayList<Location>();
		for(Coordinates coord : coordinates) {
			locations.add(new Location(coord.getLongitude(), coord.getLatitude()));
		}
		TyphoonCoordinationBean coordBean = new TyphoonCoordinationBean(type.getId(), locations);
		return coordBean;
	}
}
