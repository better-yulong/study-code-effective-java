package org.framework.aoe.spi.logger;

import java.util.Iterator;
import java.util.ServiceLoader;

public class LoggerFactory {
	
	public LoggerFactory(){
	}
	
	public static Logger getLogger(){
		Logger logger = null ;
		
		ServiceLoader<Logger> serviceLoader = ServiceLoader.load(Logger.class);
		Iterator<Logger> loggers = serviceLoader.iterator();
		if(loggers.hasNext()){
			logger = loggers.next();
		}
		return logger;
	}

}
