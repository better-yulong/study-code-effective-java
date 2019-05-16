package com.zyl.effective.demo.third.singleton;

import java.io.Serializable;

/**
 * 对于SingletonTest2.getInstance()的所有调用都会返回同一个对象，不会合建新的实例（但上述AccessibleObjet.setAccessible 也同样存在）。
 * 公有域方法的好处是组成类的成员声明表明该类是一个Singleton：final的静态域，所以该域总是包含相同的对象引用。
 * 公有域方法在性能上不再有任何优势：现代JVM实现都能够将静态工厂方法的调用内联化。
 * 
 * */
public class SingletonTest2 implements Serializable{
	
	private final static SingletonTest2 st2 =  new SingletonTest2();
	
	private SingletonTest2(){
	}

	public SingletonTest2 getInstance(){
		return st2 ;
	}
	
	//实现了Serializable接口的单例，为避免反序列化破坏单例，需添加该方法
	private Object readResolve(){
		return st2 ;
	}
	
	
}
