package com.test.jvm.concurrent;

public class VolatileCalTest {
	public static volatile int a = 0;
	
	public final static int THREAD_COUNTS = 20 ;
	
	public static void increase(){
		a++;
	}
	
	public static void main(String[] args) {
		Thread[] threads = new Thread[THREAD_COUNTS];
		for(int i=0 ; i<threads.length; i++){
			threads[i] = new Thread(new Runnable(){

				@Override
				public void run() {
					for (int i=0 ;i<10000; i++){
						increase();
					}
				}
				
			});
			threads[i].start();
		}
		
		while(Thread.activeCount()>1){
			Thread.yield();
		}
		
		System.out.println(a);

	}

}

/****
 * 即volatile变量在各个线程的工作线程可存在不一致，但是其他线程在使用前需根据主内存刷新工作内存，对于执行引擎而言可认为不存在一致情况。但是Java的运行并非原子操作，导致valatile变量的运行并发并不一定安全。
 * 
 * 20个线程，每个线程运行10000次，如a++线程安全则结果应该为200000，但实际每次运行结果不一样且均远远小于200000
 * */
