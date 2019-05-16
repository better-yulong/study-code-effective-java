package com.test.jvm.concurrent;

/***
 * 验证同步发生死锁:
 * 运行后发生无限等待，而通过jstack -l 输入线程栈，发现t1、t2为BLOCKED，而日志下方有 Found one Java-level deadlock
 * 
 * */
public class ThreadDeadLockTest {
	
	public static Object obj1 = new Object();
	
	public static Object obj2 = new Object();
	
	public static boolean switchFlag1 = false ;

	public static void main(String[] args) {
		final SynchronizedObject sobject = new SynchronizedObject();
		
		try{
			final Thread t1 = new Thread(new Runnable(){

				@Override
				public void run() {
					synchronized(obj1){
						System.out.println(Thread.currentThread().getName() + "get obj1 lock....");
						while(!switchFlag1);
						synchronized(obj2){
							System.out.println(Thread.currentThread().getName() + "get obj2 lock....");
						}
					}
				}
				
			});
			
			t1.setName("t1");
			t1.start();
			Thread.currentThread().sleep(2000);
			
			Thread t2 = new Thread(new Runnable(){

				@Override
				public void run() {
					synchronized(obj2){
						switchFlag1 = true ;
						System.out.println(Thread.currentThread().getName() + "get obj1 lock....");
						synchronized(obj1){
							System.out.println(Thread.currentThread().getName() + "get obj2 lock....");
						}
					}
				}
				
			});
			t2.setName("t2");
			t2.start();
			
		}catch(Exception e){
			
		}
		
	}
	

}


/***
 * 运行结果：
 * 
t1get obj1 lock....
t2get obj1 lock....
之后无限等待...
 * 
 * 
 * */
