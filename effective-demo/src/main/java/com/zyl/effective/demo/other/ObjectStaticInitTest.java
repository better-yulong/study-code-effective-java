package com.zyl.effective.demo.other;

public class ObjectStaticInitTest {

	public static void main(String[] args) {
		staticFunction();
	}
	
	static ObjectStaticInitTest os = new ObjectStaticInitTest();
	
	static{
		System.out.println("1");
	}
	
	{
		System.out.println("2");
	}
	
	ObjectStaticInitTest(){
		System.out.println("3");
		System.out.println("a=" + a + ",b=" + b);
	}

	public static void staticFunction(){
		System.out.println("4");
		System.out.println("b=" + b);
	}
	
	
	int a = 110 ;
	static int b = 112;
}

/**
 * 结果：	2
			3
			a=110,b=0
			1
			4
 *          b=112
 * 
 * 分析：
 * */
