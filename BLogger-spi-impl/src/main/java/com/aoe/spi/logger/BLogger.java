package com.aoe.spi.logger;

import org.framework.aoe.spi.logger.Logger;

public class BLogger implements Logger {
	public BLogger(){
	}

	public void debug(String logger) {
		System.out.println("BLogger-->debug: " + logger);
		
	}

	public void info(String logger) {
		System.out.println("BLogger-->info: " + logger);
		
	}

	public void warn(String logger) {
		System.out.println("BLogger-->warn: " + logger);
		
	}

	public void error(String logger) {
		System.out.println("BLogger-->error: " + logger);
		
	}

}
