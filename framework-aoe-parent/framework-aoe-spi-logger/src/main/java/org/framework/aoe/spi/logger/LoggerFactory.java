package org.framework.aoe.spi.logger;

import java.util.ArrayList;
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
	
/*	
 * 如若多个实现只需返回一个，可参考如上添加根据入参返回不同实现。
 * 而如若需同时支持从个实现，而考虑基于参考如下实现类   类责任链模式 ，
	private static ArrayList list ;
    static {
        list = new ArrayList();

        ServiceLoader loader = ServiceLoader.load(Logger.class);
        Iterator it = loader.iterator();
        while (it.hasNext()) {
            list.add(it.next());
        }
    }
    
    public static void process(Properties options) {
        for (Handler handler : list) {
            if (handler.accept(options)) {
                handler.execute();
            }
        }
    }
    
    
    */

}
