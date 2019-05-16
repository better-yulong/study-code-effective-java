package com.test.jvm;

/**
 * java -Xss64k com/test/jvm/StackOverFlow
 * 结果：	Stack length:961
 * 		java.lang.StackOverflowError
		at com.test.jvm.StackOverFlow.stackOverFlow(StackOverFlow.java:19)
 * */
public class StackOverFlow {

	private static int i = 1;
	public static void main(String[] args) {
		StackOverFlow sof = new StackOverFlow();
		try{
			sof.stackOverFlow();
		}catch(Throwable e){ //是运行是异常，Exception无法捕获
			System.out.println("Stack length:" + i+ ";") ;
			throw e;
		}
	}
	
	private static void stackOverFlow(){
		i++;
		stackOverFlow();
	}

}
