package com.test.jvm;

import java.util.ArrayList;

public class GenericTypesTest2<T,M,N> {
	
	final T t = null; //static不用使用泛型，因类型不确定
	static String s = "1";
	public final static String ss = "1";

	
	public <A> T testGenericTypeClear(A a,ArrayList<T> b,ArrayList<A> c,T d){
		System.out.println(a.toString());
		ArrayList<Integer> list0 = new ArrayList<Integer>();
		Integer temp0 = list0.get(0);  
		ArrayList<T> list = new ArrayList<T>();
		T temp = list.get(0);  //编译后反编译Class文件实际就是对get返回的Object对象做的强制转换成Integer对象。
		return temp;
	}
	
	public void testGenPass(T t){
		ArrayList<N> list = new ArrayList<N>();
		System.out.println(list.hashCode());
		
	}
	
	public static void main(String[] args){
		System.out.print(GenericTypesTest2.s.getClass().getModifiers());
		System.out.print(GenericTypesTest2.ss.getClass().getModifiers());
	}

}
