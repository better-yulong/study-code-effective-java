package com.test.jvm.concurrent;

/***
 * 验证线程sleep之后的状态，理解应仍是Running，因sleep只是让出CPU资源重新进入就绪队列等待重新获取CPU资源，
 * 实际jstack 生成日志显示状态为：TIMED_WAITING （有限期等待）
 * 
 * */
public class ThreadInterruptTest {
	

	public static void main(String[] args) {
		final SynchronizedObject sobject = new SynchronizedObject();
		
		try{
			final Thread t1 = new Thread(new Runnable(){

				@Override
				public void run() {
					try {
						System.out.println("sleep...");
						//Thread.currentThread().sleep(100000);  //t2中调用t1.interrupt时，t2继续运行但t1会抛出java.lang.InterruptedException:
						//synchronized(sobject){sobject.wait();} //t2中调用t1.interrupt时，t2继续运行但t1会抛出java.lang.InterruptedException:
						long i=0 ;
						while(++i<1000000000l);
						System.out.println("restart...");
						//Thread.currentThread().sleep(1000);
						synchronized(sobject){
							sobject.wait();
						}
						System.out.println("restart 2...");
					}catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			});
			
			t1.setName("t1");
			t1.start();
			
			Thread t2 = new Thread(new Runnable(){

				@Override
				public void run() {
					try {
						t1.interrupt();
						System.out.println("interrupt...");
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
			
		}catch(Exception e){
			
		}
		
		
	}
	

}

