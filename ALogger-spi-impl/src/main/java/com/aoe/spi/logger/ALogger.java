package com.aoe.spi.logger;

import org.framework.aoe.spi.logger.Logger;

public class ALogger implements Logger {
	public ALogger(){
	}

	public void debug(String logger) {
		System.out.println("ALogger-->debug: " + logger);
		
	}

	public void info(String logger) {
		System.out.println("ALogger-->info: " + logger);
		
	}

	public void warn(String logger) {
		System.out.println("ALogger-->warn: " + logger);
		
	}

	public void error(String logger) {
		System.out.println("ALogger-->error: " + logger);
		
	}

}
