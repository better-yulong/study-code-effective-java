package com.zyl.effective.demo.third.singleton;

/**
 * 运行结果：
 * SUCCESS
 * FAIL
 * 
 * */
public class SingletonEnumTest {

	public static void main(String[] args) {
		System.out.println(SingletonEnum.getValue("00"));
		System.out.println(SingletonEnum.getValue("99"));

	}

}
