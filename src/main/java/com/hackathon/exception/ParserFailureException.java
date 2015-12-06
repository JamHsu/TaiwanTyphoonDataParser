package com.hackathon.exception;

public class ParserFailureException extends Exception {

	private static final long serialVersionUID = 8403823972883557113L;
	
	public ParserFailureException(String msg) {
		super(msg);
	}
}
