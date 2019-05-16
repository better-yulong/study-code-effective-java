package com.test.jvm;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;


public class OnlineGC {

	public static void main(String[] args) throws Exception, IllegalAccessException {
		
		Object[] bb =new ByteBuffer[69000];
		 int i=0;
		 	while(true){
		 		TimeUnit.SECONDS.sleep(1);
		 		if(i%10==1){
		 			String s = String.valueOf(i);
		 		}
				bb[i++] = new Object();
		 	}
	}

}
