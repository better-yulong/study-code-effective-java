package com.test.jvm.concurrent;

/***
 * 验证非同步情况先resume线程，后suspend线程，是否如预期发生死锁。
 * 结果：线程假死无法正常结束，jstack日志显示t1、t2均为 WAIT状态，即无限期等待
 * 
 * */
public class ThreadLockTest2 {
	
	public static boolean resumeExeFlag = false ;

	public static void main(String[] args) {
		final SynchronizedObject sobject = new SynchronizedObject();
		
		
		try{
			final Thread t1 = new Thread(new Runnable(){

				@Override
				public void run() {
					sobject.printString();
				}
				
			});
			
			t1.setName("t1");
			t1.start();
			
			Thread t2 = new Thread(new Runnable(){

				@Override
				public void run() {
					System.out.println("t2 start ...");
					t1.resume();  
					resumeExeFlag = true;
					System.out.println("t2 start ,resume status ...");
					sobject.printString();
					
				}
				
			});
			t2.setName("t2");
			t2.start();
			
		}catch(Exception e){
			
		}
		
	}
	
	static class SynchronizedObject{
		 public void printString(){
			System.out.println("printString...");
			if(Thread.currentThread().getName().equals("t1")){
				System.out.println("t1 start...");
				while(!resumeExeFlag);
				Thread.currentThread().suspend();
				System.out.println("t1 resume...");

			}
		}
	}


}


/***
 * 注：static class 静态类(Java) 一般情况下是不可以用static修饰类的。如果一定要用static修饰类的话,通常static修饰的是匿名内部类。
 * 
 * 运行结果：
 * 
printString...
t1 start...
t2 start ...
t2 start ,resume status ...
printString...
之后一直等待
 * 
 * 
 * */
