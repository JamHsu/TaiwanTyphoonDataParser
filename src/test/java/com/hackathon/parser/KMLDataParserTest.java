package com.hackathon.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.hackathon.model.typhoon.kml.KMLData;
import com.hackathon.model.typhoon.kml.KMLFolder;
import com.hackathon.parser.impl.KMLDataParser;

public class KMLDataParserTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRead() {
		KMLDataParser parser = new KMLDataParser();
		String filePath = "testData/fifows_typhoon.kml";
		try {
			parser.parse(filePath);
			KMLData kmlData = parser.getKMLData();
			for(KMLFolder kmlFolder : kmlData.getKMLFolders()) {
				assertEquals("颱風消息", kmlFolder.getName());
				kmlFolder.dump();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
