package com.test.jvm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest2 {
	
	interface IHello2{
		void sayHello();
	}
	
	static class Hello2 implements IHello2,ITest2{

		@Override
		public void sayHello() {
			System.out.println("hello world ");
		}

		@Override
		public void test() {
		}
	}
	
	protected interface ITest2{
		void test();
	}

	static class DynamicProxy2 implements InvocationHandler{
		
		public DynamicProxy2(Object originalObj){
			this.originalObj = originalObj ;
		}
		Object originalObj;
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.print("welcome ");
			return method.invoke(originalObj, args);
		}
		
	}
	public static void main(String[] args) {
		 //生成$Proxy0的class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IHello2 iHello = (IHello2) Proxy.newProxyInstance(IHello2.class.getClassLoader(),
                new Class[]{IHello2.class} ,
                new DynamicProxy2(new Hello2()));
        iHello.sayHello();
	}

}
