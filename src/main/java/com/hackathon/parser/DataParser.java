package com.hackathon.parser;

import com.hackathon.exception.WetherDataParseException;

public interface DataParser {
	
	public void parse(String filePath) throws WetherDataParseException;
}
