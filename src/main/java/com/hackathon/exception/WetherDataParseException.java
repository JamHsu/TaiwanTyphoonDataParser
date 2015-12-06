package com.hackathon.exception;

public class WetherDataParseException extends Exception {

	private static final long serialVersionUID = 6854562686584250701L;

	public WetherDataParseException(String string, Exception e) {
		super(string, e);
	}

}
