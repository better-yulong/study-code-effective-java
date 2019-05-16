package com.zyl.effective.demo.third.singleton;

/**
 * 私有构造器仅被调用一次，用来实例化公有的静态final域  SingletonTest1.st1 。
 * 因缺少公有或受保护的构造器，可保证SingletonTest1全局唯一性：一旦SingletonTest1 类被实例化只会存在一个SingletonTest1实例。
 * 但是客户端仍有可能借助 AccessibleObjet.setAccessible方法，通过反射机制调用私有构造器，即破坏单例模式。
 * 如若想抵御此种攻击，则可修改构造器，使其在被要求第二次创建实例时抛出异常。
 * 
 * **/
public class SingletonTest1 {
	
	public final static SingletonTest1 st1 = new SingletonTest1();
	
	private SingletonTest1(){
		
	}

}
