package com.hackathon.parser.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.hackathon.constant.KMLTag;
import com.hackathon.exception.WetherDataParseException;
import com.hackathon.model.typhoon.kml.CanvasType;
import com.hackathon.model.typhoon.kml.Coordinates;
import com.hackathon.model.typhoon.kml.KMLData;
import com.hackathon.model.typhoon.kml.KMLFolder;
import com.hackathon.model.typhoon.kml.Placemark;
import com.hackathon.parser.DataParser;
import com.hackathon.util.XMLUtil;


public class KMLDataParser implements DataParser {
	
	private static final String CoordinateSeparate = "\n";
	private Logger logger = Logger.getLogger(KMLDataParser.class);
	private KMLData kmlData = new KMLData();
	
	@Override
	public void parse(String filePath) throws WetherDataParseException {
		logger.debug("---- start parsing KML ----");
		try {
			Document kmlDoc = XMLUtil.readXMLAsDoc(filePath);
			Node docNode = kmlDoc.getElementsByTagName(KMLTag.Document).item(0);
			
			NodeList childNodes = docNode.getChildNodes();
			for(int i = 0; i < childNodes.getLength(); i++) {
				Node node = childNodes.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					String nodeName = node.getNodeName();
					if(KMLTag.Folder.equals(nodeName)) {
						KMLFolder kmlFolder = parseKMLFolder(node);
						kmlData.addKMLFolder(kmlFolder);
					}
				}
				
			}
					
			
		} catch (Exception e) {
			throw new WetherDataParseException("Parse kml file failed:" + filePath, e);
		} finally {
			logger.debug("---- End parsing KML ----");
		}
	}
	
	private KMLFolder parseKMLFolder(Node kmlFolderNode) {
		logger.debug("---- start parsing <Folder> ----");
		NodeList childNodes = kmlFolderNode.getChildNodes();
		KMLFolder kmlFolder = new KMLFolder();
		
		for(int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			String nodeName = childNode.getNodeName();
			String nodeText = childNode.getTextContent();
			if(KMLTag.Name.equals(nodeName)) {
				kmlFolder.setName(nodeText);
				
			} else if(KMLTag.Description.equals(nodeName)) {
				kmlFolder.setDescription(nodeText);
				
			} else if(KMLTag.Placemark.equals(nodeName)) {
				Placemark placemark = parsePlacemark(childNode);
				kmlFolder.addKMLEntity(placemark);
				logger.debug("add placemark");
				
			} else if(KMLTag.Folder.equals(nodeName)) {
				KMLFolder subFolder = parseKMLFolder(childNode);
				kmlFolder.addKMLEntity(subFolder);
				logger.debug("add folder");
				
			}
		}
		logger.debug("---- end parsing <Folder> ----");
		return kmlFolder;
	}

	private Placemark parsePlacemark(Node placemarkNode) {
		logger.debug("---- start parsing <Placemark> ----");
		NodeList childNodes = placemarkNode.getChildNodes();
		Placemark placemark = new Placemark();
		for(int j = 0; j < childNodes.getLength(); j++) {
			if(childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
				Node childNode = childNodes.item(j);
				String nodeName = childNode.getNodeName();
				String nodeText = childNode.getTextContent();
				
				if(KMLTag.Name.equals(nodeName)) {
					placemark.setName(nodeText);
				} else if(KMLTag.Description.equals(nodeName)) {
					placemark.setDescription(nodeText);
				} else if(CanvasType.isCanvasType(nodeName)) {
					placemark.setCanvasType(CanvasType.valueOf(nodeName));
					List<Coordinates> coordList = parseCoordinates(childNode);
					placemark.addCoordinates(coordList);
				}
			} 
		}
		logger.debug("---- end parsing <Placemark> ----");
		return placemark;
	}
	
	private List<Coordinates> parseCoordinates(Node canvasNode) {
		logger.debug("----  start parsing <Coordinates> ----");
		List<Coordinates> coordinatesList = new ArrayList<Coordinates>();
		NodeList childNodes = canvasNode.getChildNodes();
		for(int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			String nodeName = childNode.getNodeName();
			String nodeText = childNode.getTextContent();
			if(KMLTag.Coordinates.equals(nodeName)) {
				String[] coordinatesAry = nodeText.split(CoordinateSeparate);
				for(int j = 0; j < coordinatesAry.length; j++) {
					if(coordinatesAry[j].isEmpty()) {
						continue;
					}
					Coordinates coord = Coordinates.parseCoordinates(coordinatesAry[j]);
					coordinatesList.add(coord);
				}
			} else if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				coordinatesList.addAll(parseCoordinates(childNode));
			}
		}
		logger.debug("----  end parsing <Coordinates> ----");
		return coordinatesList;
	}
	
	public KMLData getKMLData() {
		return kmlData;
	}
}
