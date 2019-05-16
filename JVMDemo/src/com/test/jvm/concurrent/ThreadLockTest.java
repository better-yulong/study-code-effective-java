package com.test.jvm.concurrent;

/***
 * 验证同步情况下：
 * 1、先suspend后resume线程：可正常唤醒
 * 2、先suspend后不resume线程：线程阻塞
 * 
 * */
public class ThreadLockTest {

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
			Thread.sleep(2000);
			
			Thread t2 = new Thread(new Runnable(){

				@Override
				public void run() {
					System.out.println("t2 start ...");
					t1.resume();  
					//若把 t1.resume();注释掉，则会因t1线程暂停，t2线程调用 sobject.printString（）会无法获取到锁被阻塞，通过jstack 根据线程栈分析可发现
					//"Thread-1" prio=6 tid=0x04e56000 nid=0x174 waiting for monitor entry [0x0523f000]  ; java.lang.Thread.State: BLOCKED (on object monitor)
					System.out.println("t2 start ,resume success ...");
					sobject.printString();
					
				}
				
			});
			t2.setName("t2");
			t2.start();
			
		}catch(Exception e){
			
		}
		
	}

}

class SynchronizedObject{
	synchronized public void printString(){
		System.out.println("printString...");
		if(Thread.currentThread().getName().equals("t1")){
			System.out.println("t1 start...");
			Thread.currentThread().suspend();
			System.out.println("t1 resume...");

		}
	}
}


/***
 * 运行结果（未注释：t1.resume()则方法正常线束；若注释t1.resume()则线程阻塞，通过jstack -l可发现t2为BLOCKED状态）：
 * 
printString...
t1 start...
t2 start ...
t2 start ,resume success ...
t1 resume...
printString....

 * 
 * 
 * */
