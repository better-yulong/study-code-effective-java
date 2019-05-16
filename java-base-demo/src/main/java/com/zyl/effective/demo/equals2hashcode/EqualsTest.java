package com.zyl.effective.demo.equals2hashcode;

import java.util.ArrayList;

public class EqualsTest {

	public static void main(String[] args) {
		Object o = new Object();
		ArrayList<Object> a1 = new ArrayList<Object>();
		a1.add(o);
		ArrayList<Object> a2 = new ArrayList<Object>();
		a2.add(o);
		System.out.println(a1.equals(a2)); // 结果为:true
		
		
		Object o3 = new Object();
		ArrayList<Object> a3 = new ArrayList<Object>();
		a3.add(o3);
		Object o4 = new Object();
		ArrayList<Object> a4 = new ArrayList<Object>();
		a4.add(o4);
		System.out.println(a3.equals(a4));// 结果为:false
	}

}
