package com.test.jvm;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class DirectMemoryOOM {

	public static void main(String[] args) throws Exception, IllegalAccessException {
		
		Class<?> c;
		c = Class.forName("java.nio.Bits");
		Field maxMemory;
		maxMemory = c.getDeclaredField("maxMemory");
		maxMemory.setAccessible(true);
		Field reservedMemory = c.getDeclaredField("reservedMemory");
		reservedMemory.setAccessible(true);
		
        
      	Long maxMemoryValue = (Long)maxMemory.get(null);
      	System.out.println(maxMemoryValue); 
      	DirectOOMThread dt = new DirectOOMThread();
      	dt.start();
      	TimeUnit.SECONDS.sleep(1500);
        Long reservedMemoryValue = (Long)reservedMemory.get(null);
        System.out.println(reservedMemoryValue);
      	
	}
	
	static class DirectOOMThread extends Thread{
		 @Override
		    public void run() {
			 ByteBuffer[] bb =new ByteBuffer[69];
			 int i=0;
			 	while(true){
			      	try {
						this.sleep(1000);
						bb[i++]= ByteBuffer.allocateDirect(1024*1024*1);
					} catch (InterruptedException e) {
					}
					
			 	}
		 }
	}

}
