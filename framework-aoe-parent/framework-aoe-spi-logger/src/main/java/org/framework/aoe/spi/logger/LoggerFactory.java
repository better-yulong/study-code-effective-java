package org.framework.aoe.spi.logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ServiceLoader;

public class LoggerFactory {
	
	private static ArrayList<Logger> list ;
    static {
        list = new ArrayList<Logger>();

        ServiceLoader loader = ServiceLoader.load(Logger.class);
        Iterator<Logger> it = loader.iterator();
        while (it.hasNext()) {
            list.add(it.next());
        }
    }
    
	public LoggerFactory(){
	}
	
	public static Logger getLogger(){
		System.out.println(new Date());
		return list!=null?list.get(0):null;
	}
	
	public static void logAllInfo(String info){
		for(Logger logger:list){
			logger.info("logAllInfo:" + info);
		}
	}
	
/*	
 * 如若多个实现只需返回一个，可参考如上添加根据入参返回不同实现。
 * 而如若需同时支持从个实现，而考虑基于参考如下实现类   类责任链模式 ，
 * 参考：https://my.oschina.net/redraiment/blog/105209
 * 
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
