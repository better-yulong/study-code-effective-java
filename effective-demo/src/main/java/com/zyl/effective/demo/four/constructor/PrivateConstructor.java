package com.zyl.effective.demo.four.constructor;

public class PrivateConstructor {

	private PrivateConstructor(){
		//防止通过序列化或反射实例化
		throw new AssertionError();
	}
}
