package com.test.jvm.enums;

import com.test.jvm.enums.EnumMain.EnumTest;

public class EnumMain2 {

	public static void main(String[] args) {
		System.out.println(EnumTest2.INSTANCE_A.getValue());
		System.out.println(EnumTest2.INSTANCE_A.getType());
		System.out.println(EnumTest2.INSTANCE_B.getValue());
		System.out.println(EnumTest2.INSTANCE_B.getType());
		
		EnumTest2 et = EnumTest2.INSTANCE_A;
		System.out.println(et.getType());
	}
	
	enum EnumTest2 {
		INSTANCE_A(0,"type0"),
		INSTANCE_B(1,"type1");
		
		EnumTest2(int value,String type){
			this.value = value;
			this.type = type ;
		}
		
		int value;
		public int getValue(){
			return value;
		}
		
		String type;
		public String getType(){
			return type;
		}
	}

}


/*运行结果：
0
type0
1
type1
type0

 * 1、编译器会自动生成一个继承java.lang.Enum的类EnumT，且生成默认的private构造方法，即不允许调用。
 * 2、INSTANCE 可看作EnumT类的变量，可等价理解为：public static final EnumT INSTANCE.
 * 
 * 可理解：Enum未提供可供外部访问的构造函数；而INSTANCE_A(0,"type0") 则可认为类EnumMain2的实例对象INSTANCE_A 
 * EnumTest2(int value,String type) 即为其构造函数，访问修饰默认为private且不允许修改为非private
 * 
 * 总是来说：Enum 枚举是一个构造函数能且只能是private的类，其成员变量即默认为该类的实例化对象，不允许外部通过构造函数。
 * 枚举无法通过反射或其他方法破坏单例，且枚举线程安全
 * 
 * **/

