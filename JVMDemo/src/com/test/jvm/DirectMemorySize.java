package com.test.jvm;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicLong;

public class DirectMemorySize {

	public static void main(String[] args) throws Exception, IllegalAccessException {
		Class<?> c;
		c = Class.forName("java.nio.Bits");
		Field maxMemory;
		maxMemory = c.getDeclaredField("maxMemory");
		maxMemory.setAccessible(true);
		Field reservedMemory = c.getDeclaredField("reservedMemory");
		reservedMemory.setAccessible(true);
		//jdk6、jdk7 如下返回的为long类型
		Long maxMemoryValue = (Long)maxMemory.get(null);
		Long reservedMemoryValue = (Long)reservedMemory.get(null);
		System.out.println(maxMemoryValue);
		System.out.println(reservedMemoryValue);
		
		//jdk8运行时如下返回的为AtomicLong类型，运行会报类型转换异常
		/*Long maxMemoryValue = (Long)maxMemory.get(null);
		AtomicLong reservedMemoryValue = (AtomicLong)reservedMemory.get(null);
		System.out.println(maxMemoryValue);
		System.out.println(reservedMemoryValue);*/
	}

}
