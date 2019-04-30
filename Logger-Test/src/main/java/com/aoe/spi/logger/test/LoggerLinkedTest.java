package com.aoe.spi.logger.test;

import org.framework.aoe.spi.logger.Logger;
import org.framework.aoe.spi.logger.LoggerFactory;

public class LoggerLinkedTest {

	private final static Logger logger = LoggerFactory.getLogger();
	
	public static void main(String[] args) {
		logger.debug("this is use debug... ");
		logger.info("this is use info... ");
		logger.warn("this is use warn... ");
		logger.error("this is use error... ");
	}

}

/**
 * 
 * 
 * 
 * **/
