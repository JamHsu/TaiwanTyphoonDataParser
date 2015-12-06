package com.hackathon.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpDownloader {
	
	public static File downloadPageAsFile(String url, String destionation) throws IOException {
		URL urlObj;
		urlObj = new URL(url);
		File file = new File(destionation);
		FileUtils.copyURLToFile(urlObj, file);
		return file;
	}
	
	@SuppressWarnings("deprecation")
	public static File downloadFile(String url, String destination) throws ClientProtocolException, IOException {
		
		@SuppressWarnings("resource")
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		File file = new File(destination);
		InputStream in = response.getEntity().getContent();
		BufferedInputStream bin = new BufferedInputStream(in);
		OutputStream os = new FileOutputStream(file);
		IOUtils.copy(bin, os);
		bin.close();
		in.close();
		os.close();
		client.getConnectionManager().shutdown();
		return file;
		
	}
}
