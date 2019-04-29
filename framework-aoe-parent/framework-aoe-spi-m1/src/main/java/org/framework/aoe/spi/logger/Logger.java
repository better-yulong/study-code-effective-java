package org.framework.aoe.spi.logger;


public interface Logger {
	
	public void debug(String logger);
	
	public void info(String logger);
	
	public void warn(String logger);
	
	public void error(String logger);
}
