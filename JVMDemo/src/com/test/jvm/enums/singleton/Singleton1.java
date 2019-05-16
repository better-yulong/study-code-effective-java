package com.test.jvm.enums.singleton;

class Singleton1 {
	
	private final static Singleton1 s1= new Singleton1();
	
	private Singleton1(){}
	 
	public Singleton1 getSingleton(){
		return s1 ;
	}


}

/**
 * 单例模式1：线程安全，类加载是直接实例化对象，但可通过反射调整构造方法或序列化破坏单例。
 * 1、反射破坏比较好理解，即调整构造方法为可访问setAccessible(true)，然后反射调用构造函数即可创建新对象。
 * 2、序列化破坏：即把对象通过ObjectOutputStream 输出到文件，然后通过ObjectInputStream.readOrdinaryObjec
 * 还原对象时合建一个新对象，因readOrdinaryObject底层也是反射调用默认构造函数
 * 
 * 对于反序列化
 * 
 * https://www.cnblogs.com/kexianting/p/8977990.html
 * https://www.cnblogs.com/linjiaxin/p/7923135.html

 * */
