package com.test.jvm;

public class TestStaticDispatch2 {

	public void sayHello(Human guy){
		System.out.println("hello.guy");
	}
	
	public static void main(String[] args) {
		Human man = new Man();
		Human woman = new Woman();
		TestStaticDispatch2 ts = new TestStaticDispatch2();
		ts.sayHello(man);
		ts.sayHello(woman);
	}

}
class Human{
}

class Man extends Human{
}
class Woman extends Human{
}

/**执行结果（静态分派）：
 * hello.guy
   hello.guy
 * */
