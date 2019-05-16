package com.test.jvm.concurrent;

public class ThreadLocalTest {
	
	static final ThreadLocal<String> t1 = new ThreadLocal<String> ();
	static final ThreadLocal<String> t2 = new ThreadLocal<String> ();
	static final ThreadLocal<String> t3 = new ThreadLocal<String> ();
	static final ThreadLocal<String> t4 = new ThreadLocal<String> ();

	public static void main(String[] args) {
		
		Thread[] ts = new Thread[2];
		for(int i=0 ; i<ts.length; i++){
			ts[i] = new Thread(new Runnable(){

				@Override
				public void run() {
					t1.set("t1");
					String t2s = new String("t2");
					t2.set(t2s);
					t3.set("t3");

					System.out.println(Thread.currentThread().hashCode() + t1.get());
					System.out.println(Thread.currentThread().hashCode() + t2.get());
					System.out.println(Thread.currentThread().hashCode() + t3.get());
					System.out.println(Thread.currentThread().hashCode() + t4.get());
				}
				
			});
			ts[i].start();
		}
	}

}
