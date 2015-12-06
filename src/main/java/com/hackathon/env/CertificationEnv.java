package com.hackathon.env;

import com.hacker.bean.request.AuthenticathDeveloper;

public interface CertificationEnv {

	public final static AuthenticathDeveloper authEnity = new AuthenticathDeveloper(
			CertificationEnv.CLIENT_ID, CertificationEnv.SECRET, CertificationEnv.PERATION);
	
	public final static String CLIENT_ID = "0d656992b4204d91b75e28cd44db8388";
	
	public final static String SECRET = "cbc9b3a05dc141b783e8dfbbb354dad3";
	
	public final static String PERATION = "Android";
}
