package com.test.jvm.concurrent;

/***
 * 验证线程sleep之后的状态，理解应仍是Running，因sleep只是让出CPU资源重新进入就绪队列等待重新获取CPU资源，
 * 实际jstack 生成日志显示状态为：TIMED_WAITING （有限期等待）
 * 
 * */
public class ThreadSleepTest {
	

	public static void main(String[] args) {
		final SynchronizedObject sobject = new SynchronizedObject();
		
		try{
			final Thread t1 = new Thread(new Runnable(){

				@Override
				public void run() {
					try {
						Thread.currentThread().sleep(100000);
					} catch (InterruptedException e) {
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
						Thread.currentThread().sleep(100000);
					} catch (InterruptedException e) {
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

