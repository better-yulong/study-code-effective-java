package com.test.jvm.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
	
	public static int normao_i = 0 ;
	
	public static AtomicInteger atomic_i = new AtomicInteger(0);
	
	private final static int THREAD_COUNT = 20 ;	
	
	public static void increase(){
		normao_i++;
		atomic_i.incrementAndGet();
	}
	

	public static void main(String[] args) {
		Thread[] ts = new Thread[THREAD_COUNT];
		for(int i=0;i<ts.length;i++){
			ts[i] = new Thread(new Runnable(){

				@Override
				public void run() {
					for(int i=0;i<1000;i++){
						increase();
					}
				}
				
			});
			ts[i].start();
		}
		
		while(Thread.activeCount()>1){
			Thread.yield();
		}
		
		System.out.println("normao_i:" + normao_i);
		System.out.println("atomic_i:" + atomic_i);
	}
}

/***
 * 运行结果：
 *  normao_i:19931
	atomic_i:20000
 * */
