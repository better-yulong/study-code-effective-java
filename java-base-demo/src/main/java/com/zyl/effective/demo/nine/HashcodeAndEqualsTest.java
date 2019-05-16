package com.zyl.effective.demo.nine;

public class HashcodeAndEqualsTest {

	public static void main(String[] args) {
		String a = new String("1");
		String b = new String("1");
		System.out.println(a==b);
		System.out.println(a.equals(b));
		System.out.println(a.hashCode());
		System.out.println(a.hashCode()==b.hashCode());

	}

}
/*结果：
	false
	true
	49
	true
*/
