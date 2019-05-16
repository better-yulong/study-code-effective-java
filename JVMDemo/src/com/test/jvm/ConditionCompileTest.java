package com.test.jvm;

public class ConditionCompileTest {

	public static void main(String[] args) {
		if(Integer.valueOf(1)==Integer.valueOf(1)){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
		/**
		 * 编译后只剩  System.out.println("true");
		 * */
		
		while(1==1){   
			System.out.println("while true");
		}
		/* while(true) 编译后被捡的为for(;;) */
		
		
		
		/*while(false){   
			System.out.println("while true");
		}*/
		/*com\test\jvm\ConditionCompileTest.java:12: 错误: 无法访问的语句                                                                  
        while(false){
                    ^
1 个错误  */                                                                                                                       


	}

}
