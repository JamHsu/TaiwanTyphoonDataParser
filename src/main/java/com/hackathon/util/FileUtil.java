package com.hackathon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.log4j.Logger;

public class FileUtil {
	
	private final static Logger logger = Logger.getLogger(FileUtil.class);
	
	/**
     * Unzip it
     * @param zipFile input zip file
     * @param output zip file output folder
	 * @throws IOException 
     */
    public static void unzipFile(String zipFile, String outputFolder) throws IOException{

    	byte[] buffer = new byte[1024];
    	
    	try{
    		
	    	//create output directory is not exists
	    	File folder = new File(outputFolder);
	    	if(!folder.exists()){
	    		folder.mkdir();
	    	}
	    		
	    	//get the zip file content
	    	ZipInputStream zis = 
	    		new ZipInputStream(new FileInputStream(zipFile));
	    	//get the zipped file list entry
	    	ZipEntry ze = zis.getNextEntry();
	    		
	    	while(ze!=null){
	    			
	    	   String fileName = ze.getName();
	           File newFile = new File(outputFolder + File.separator + fileName);
	                
	           logger.info("file unzip : "+ newFile.getAbsoluteFile());
	                
	            //create all non exists folders
	            //else you will hit FileNotFoundException for compressed folder
	            new File(newFile.getParent()).mkdirs();
	              
	            FileOutputStream fos = new FileOutputStream(newFile);             
	
	            int len;
	            while ((len = zis.read(buffer)) > 0) {
	       		fos.write(buffer, 0, len);
	            }
	        		
	            fos.close();   
	            ze = zis.getNextEntry();
	    	}
	    	
	        zis.closeEntry();
	    	zis.close();
	    		
	    	logger.info("file unzip finish.");
    		
    	} catch(IOException ex){
    		throw ex; 
    	}
    }    
    
    public static File[] findFiles(String folderPath, final String endText) {
    	File folder = new File(folderPath);
    	if(folder.isFile()) {
    		throw new RuntimeException("Can not support find file.");
    	}
    	
    	File[] result = folder.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(endText);
			}
    	
    	});
    	return result;
    }

}
