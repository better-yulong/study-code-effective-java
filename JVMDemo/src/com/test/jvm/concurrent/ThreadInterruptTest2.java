package com.test.jvm.concurrent;

/***
 * 本计划验证线程进入join而导致main暂停变成串行执行后尝试interrupt，看是否可重新变成并行执行，但未验证成功
 * 一旦执行join，只会执行完t1 最后才会执行main线程。
 * 
 * */
public class ThreadInterruptTest2 {
	private static  boolean f1 = true;
	private static  boolean f2 = true;
	private static  boolean f3 = true;


	public static void main(String[] args) {
		final SynchronizedObject sobject = new SynchronizedObject();
		
		try{
			final Thread t1 = new Thread(new Runnable(){

				@Override
				public void run() {
					System.out.println("t1...");
					while(f1);
					System.out.println("t1 a..");
					long i=0 ;
					while(++i<1000000000l);
					System.out.println("t1 b..");
					f2 = false;
					System.out.println("t1 c..");
					i=0;
					while(++i<1000000000l);
					System.out.println("t1 d..");
				}
				
			});
			
			t1.setName("t1");
			t1.start();

			Thread t2 = new Thread(new Runnable(){

				@Override
				public void run() {
					try {
						System.out.println("interrupt...");
						while(f2);
						System.out.println("interrupt a...");
						t1.interrupt();
						System.out.println(t1.isAlive());
						System.out.println(t1.isInterrupted());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
			t2.setName("t2");
			t2.start();
			f1 = false;
			t1.join();
			System.out.println("main....");
			
		}catch(Exception e){
			
		}
		
		
	}
	

}

