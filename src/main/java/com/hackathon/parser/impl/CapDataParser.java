package com.hackathon.parser.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.hackathon.constant.CapTag;
import com.hackathon.exception.WetherDataParseException;
import com.hackathon.model.typhoon.cap.CapDataParseTerm;
import com.hackathon.model.typhoon.cap.TyphoonInfo;
import com.hackathon.model.typhoon.cap.TyphoonStrength;
import com.hackathon.model.typhoon.kml.Coordinates;
import com.hackathon.parser.DataParser;
import com.hackathon.util.MapCalculateUtil;
import com.hackathon.util.XMLUtil;

public class CapDataParser implements DataParser {

	private final static Logger logger = Logger.getLogger(CapDataParser.class);
	private TyphoonInfo typhoonInfo = new TyphoonInfo();
	
	@Override
	public void parse(String filePath) throws WetherDataParseException {
		logger.debug("Start paring CAP file.");
		try {
			Document doc = XMLUtil.readXMLAsDoc(filePath);
			Node infoNode = doc.getElementsByTagName(CapTag.Info).item(0);
			NodeList infoChildList = infoNode.getChildNodes();
			for(int i = 0; i < infoChildList.getLength(); i++) {
				Node node = infoChildList.item(i);
				String nodeName = node.getNodeName();
				String nodeValue = node.getTextContent();
				if(CapTag.Description.equals(nodeName)) {
					Map<CapDataParseTerm, String> result = parsingDescription(nodeValue);
					logger.debug("Setting data to object.");
					typhoonInfo.setDescription(nodeValue);
					typhoonInfo.setChtName(result.get(CapDataParseTerm.Cht_name));
					typhoonInfo.setEngName(result.get(CapDataParseTerm.Eng_name));
					
					TyphoonStrength strength = TyphoonStrength.getTyphoonStrengthByChtName(result.get(CapDataParseTerm.Strength));
					typhoonInfo.setStrength(strength);
					
					Double lat = Double.parseDouble(result.get(CapDataParseTerm.Center_loc_lat));
					Double lng = Double.parseDouble(result.get(CapDataParseTerm.Center_loc_lng));
					Coordinates centerLoc = new Coordinates(lat, lng);
					typhoonInfo.setCenterLocation(centerLoc);
					
					lat = Double.parseDouble(result.get(CapDataParseTerm.Forecast_loc_lat));
					lng = Double.parseDouble(result.get(CapDataParseTerm.Forecast_loc_lng));
					Coordinates forecastLoc = new Coordinates(lat, lng);
					typhoonInfo.setForecastLocation(forecastLoc);
					
					double angle = MapCalculateUtil.calculateAngle(
							centerLoc.getLatitude(), centerLoc.getLongitude(),
							forecastLoc.getLatitude(), forecastLoc.getLongitude());
					typhoonInfo.setTyphoonRotation(Double.valueOf(angle));
					
					typhoonInfo.setWindVelocity(Integer.parseInt(result.get(CapDataParseTerm.Speed)));
					typhoonInfo.setMaxWindVelocity(Integer.parseInt(result.get(CapDataParseTerm.Max_speed)));
					typhoonInfo.setAirpressure(Integer.parseInt(result.get(CapDataParseTerm.Airpressure)));
					typhoonInfo.setTyphoonRange(Integer.parseInt(result.get(CapDataParseTerm.Typhoon_range)));
					
					typhoonInfo.setAlertNumber(Integer.parseInt(result.get(CapDataParseTerm.AlertCnt)));
					typhoonInfo.setTyphoonNumber(Integer.parseInt(result.get(CapDataParseTerm.TyphoonNumber)));
					
					typhoonInfo.setTyphoonCenterDistance(Integer.parseInt(result.get(CapDataParseTerm.TyphoonCenterDistance)));
					typhoonInfo.setForecastTyphoonCenterDistance(Integer.parseInt(result.get(CapDataParseTerm.ForecastTyphoonCenterDistance)));
					
					String speed = "0";
					if(result.get(CapDataParseTerm.TyphoonSpeed) != null) {
						String data = result.get(CapDataParseTerm.TyphoonSpeed);
						if(data.contains("轉")) {
							data = data.substring(0, data.indexOf("轉"));
						}
						speed = data;
						logger.debug("Speed result:" + speed);
					}
					
					typhoonInfo.setTyphoonSpeed(Integer.parseInt(speed));
					
					
				}
			}
			logger.debug("End paring CAP file.");
		} catch (Exception e) {
			throw new WetherDataParseException("Parse cap file failed:" + filePath, e);
		}
	}
	
	private Map<CapDataParseTerm, String> parsingDescription(String description) {
		Map<CapDataParseTerm, String> result = new HashMap<CapDataParseTerm, String>();
		String[] descLineAry = description.split("\n");
		for(String descLine : descLineAry) {
			if(!descLine.isEmpty()) {
				for(CapDataParseTerm typhoonTerm : CapDataParseTerm.values()) {
					String checkTerm = typhoonTerm.getCheckTerm();
					if(descLine.contains(checkTerm)) {
						String value = parsingDescriptionLine(descLine, typhoonTerm);
						result.put(typhoonTerm, value);
					}
				}
			}
		}
		return result;
	}

	private String parsingDescriptionLine(String descLine, CapDataParseTerm typhoonTerm) {
		String value = null;
		String startKey = typhoonTerm.getStartKey();
		String endKey = typhoonTerm.getEndKey();
		try {
			logger.debug("Parsing descritpion line for " + typhoonTerm.getCheckTerm());
			int startIdx = descLine.indexOf(startKey) + startKey.length();
			int endIdx = endKey != null ? descLine.indexOf(endKey) : descLine.length()-1;
			endIdx = endIdx < startIdx ? descLine.lastIndexOf(endKey) : endIdx;
			value = descLine.substring(startIdx, endIdx);
			logger.debug("Parsing descritpion line result:" + value);
			
		} catch (Exception e) {
			logger.warn("parse " + typhoonTerm.getCheckTerm() + " failed:" + e.getMessage() );
		}
		return value;
	}
	
	public TyphoonInfo getTyphoonInfo() {
		return typhoonInfo;
	}

}
