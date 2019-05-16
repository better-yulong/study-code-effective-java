package com.zyl.effective.demo.six.expireRef;

public class ObjectExpireStackMemoryLeakTest {

	public static void main(String[] args) throws Exception {
		Thread.currentThread().sleep(1000*50l);
		ObjectExpireStackMemoryLeak stack = new ObjectExpireStackMemoryLeak();
		System.out.println("start push.......");
		for(int i=0 ; i<10000000; i++){
			 stack.push(new Object());
		}
		System.out.println("end push.......");
		Thread.currentThread().sleep(1000*10l);
		System.out.println("start pop.......");
		for(int i=0 ; i<10000000-10; i++){
			 stack.pop();
		}
		System.out.println("end pop.......");
		while(true){
			System.gc();
			Thread.currentThread().sleep(1000*10l);
			//stack.push(new Object());	
		}
		
	}

}

/**
 * ObjectExpireStackMemoryLeak 为两个.pop()方法，其中
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */
