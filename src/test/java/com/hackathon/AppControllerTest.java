package com.hackathon;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRun() {
		 AppController controller = new AppController();
    	 try {
    		 controller.run();
    	 } catch (Exception e) {
    		fail(e.getMessage());
    		e.printStackTrace();
    	 }


	}

}
