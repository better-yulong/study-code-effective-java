package com.test.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class TestMethodHahdle2 {
	
	 class GrandFather{
		public void thinking(){ 
			System.out.println("I'ma grandfather...");
		}
	}
	 class Father extends GrandFather{
		 public void thinking(){ 
			 System.out.println("I'ma father...");
		 }
	 }
	 class Son extends Father{
		 public void thinking(){ 
			MethodType mt = MethodType.methodType(void.class);
			try {
				MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class, "thinking", mt, getClass());
				mh.invoke(this);
			} catch (Throwable e) {
				e.printStackTrace();
			} 
			
			//  上面的代码实现效果类似于下面这行
			//  new GrandFather().thinking();
		 }
	 }

	public static void main(String[] args) throws Throwable, Throwable {
		Son son = new TestMethodHahdle2().new Son();
		son.thinking();
	}

}
/**
 * 执行结果：I'ma grandfather...
 *  1、内部类可看作是变量，若是非static内部类，则也需基于当前类的对象再去创建内部类
 * */
 