package com.test.jvm;

import java.io.IOException;
import java.io.InputStream;

/*
 * 类加载器与 instanceof 关键字演示
 * */
public class TestClassLoader {

	public static void main(String[] args) throws Exception {
		ClassLoader myLoader = new ClassLoader(){

			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class" ;
				InputStream is = getClass().getResourceAsStream(fileName);
				if(is == null){
					return super.loadClass(name);
				}
				try {
					byte[] b = new byte[is.available()];
					is.read(b);
					return defineClass(name,b,0,b.length);
				} catch (IOException e) {
					throw new ClassNotFoundException(name);
				}
			}

			@Override
			protected Class<?> findClass(String name) throws ClassNotFoundException {
				String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class" ;
				InputStream is = getClass().getResourceAsStream(fileName);
				if(is == null){
					return super.loadClass(name);
				}
				try {
					byte[] b = new byte[is.available()];
					is.read(b);
					return defineClass(name,b,0,b.length);
				} catch (IOException e) {
					throw new ClassNotFoundException(name);
				}
			}
			
			
		};
		
		Object obj = myLoader.loadClass("com.test.jvm.TestClassLoader").newInstance();
		System.out.println(obj.getClass().getClassLoader().getParent().getParent().getClass());
		System.out.println(myLoader.getClass().getClassLoader().getClass());
		System.out.println(obj.getClass());
		System.out.println(obj instanceof com.test.jvm.TestClassLoader);
		
		//ClassLoader myLoader2 = new MyBootClassLoader();
		System.out.println(System.getProperty("sun.boot.class.path"));
		System.out.println(ClassLoader.getSystemClassLoader());
	}
	
	/**
	 * 1、运行编译： javac com/test/jvm/TestClassLoader.java
	 *    报错：TestClassLoader.java:7: 错误: 编码GBK的不可映射字符             因第7行存在中文注释
	 * 
	 * 2、javac -encoding UTF-8 com/test/jvm/TestClassLoader.java  //指定字符集编译即可
	 * 3、运行：java com/test/jvm/TestClassLoader
			 class com.test.jvm.TestClassLoader
			 false
	 * 
	 * 可从表面看出类的限定描述符相同，但之后的instanceof 结果却为 false，因为obj为 myloader加载器加载实例化,即虚拟机中存在两个TestClassLoader 类。
	 * 而com.test.jvm.TestClassLoader则默认取的当类前类加载器加载的com.test.jvm.TestClassLoader类
	 * 
	 * 自定义类加载器若不想违背双亲委派模型，则只需要重写findclass方法即可，如果想违背双亲委派模型，则还需要重写loadclass方法。
	 * **/

	class MyBootClassLoader extends ClassLoader{
		
	}
}
