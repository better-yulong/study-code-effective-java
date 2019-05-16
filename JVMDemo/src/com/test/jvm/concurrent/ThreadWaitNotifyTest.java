package com.test.jvm.concurrent;

/***
 * 验证  wait、notify、notifyAll的正确用法是 synchronized(obj){ ...; obj.wait();...}，而并不是调用Thread对象的wait。
 * 
 * */
public class ThreadWaitNotifyTest {
	
	public static void main(String[] args) {
		final Object sobject = new Object();
		try{
			final Thread t1 = new Thread(new Runnable(){

				@Override
				public void run() {
					synchronized(sobject){
						try {
							System.out.println("t1 wait start ....");
							 //Thread.currentThread().wait(); 此处是尝试暂停是t1，而Thread.currentThread()获取的是t1对象的锁标记并未被任何线程持有，
							//会抛出异常IllegalMonitorStateException
							sobject.wait(100000);
							System.out.println("t1 restart ....");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			});
			
			t1.setName("t1");
			t1.start();
			t1.sleep(2000);
			Thread t2 = new Thread(new Runnable(){

				@Override
				public void run() {
					synchronized(sobject){
						try {
							System.out.println("t1 notify start ....");
							// Thread.currentThread().notify(); 此处是通知唤醒错误，而Thread.currentThread()获取的是t1对象的锁标记并未被任何线程持有，
							//会抛出异常IllegalMonitorStateException
							sobject.notifyAll();
							System.out.println("t1 notify end ....");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
 * 若使用Thread.currentThread().wait(); 此处是尝试暂停是t1，而Thread.currentThread()获取的是t1对象的锁标记并未被任何线程持有，
 * 若使用sobject.wait()，则正常运行完成：
t1 wait start ....
t1 notify start ....
t1 notify end ....
t1 restart ....
 * 
 * */
