package com.hackathon;

import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class App {
	
	private static final Logger logger = Logger.getLogger(App.class);
	
    public static void main( String[] args ) {
    	 BasicConfigurator.configure();
    	 StopWatch watch = new StopWatch();
    	 watch.start();
    	 AppController controller = new AppController();
    	 try {
    		 controller.run();
    	 } catch (Exception e) {
    		 logger.error("Program failed.", e);
    	 } finally {
    		 watch.stop();
    		 logger.info("Program finish, cost:" + watch.toString() + "");
    	 }
    }
    
}
