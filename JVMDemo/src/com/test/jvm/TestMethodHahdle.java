package com.test.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class TestMethodHahdle {
	
	static class NewOut{
		
		public void println(String in){ //同System.out.println("a");方法
			System.out.println("NewOut:" + in);
		}
	}

	public static void main(String[] args) throws Throwable, Throwable {
		Object obj = System.currentTimeMillis()%6<3?System.out:new NewOut();
		getMethodHandle(obj).invoke("print...");
		Object obj2 = System.currentTimeMillis()%6<3?System.out:new NewOut();
		getMethodHandle(obj2).invoke("print...");
	}
	
	private static MethodHandle getMethodHandle(Object receiver) throws Throwable, IllegalAccessException{
		//MethodType 代表：方法类型
		MethodType mt = MethodType.methodType(void.class,String.class);
		return MethodHandles.lookup().findVirtual(receiver.getClass(), "println", mt).bindTo(receiver);
	}

}
/**
 * 多次执行，结果可能有差异，但如下结果正好可说明问题：
 *  print...
	NewOut:print...
 * 
 * getMethodHandle 模拟的即是 invokevirtual指令的执行过程，只是它的分派并非固化在class文件的字节码，而是通过一个具体方法实现。其返回值MethodHandle对象可视为最终调用方法
 * 的一个“引用”
 * 
 * */
 