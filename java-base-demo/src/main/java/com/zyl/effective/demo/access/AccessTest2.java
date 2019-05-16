package com.zyl.effective.demo.access;

class AccessTest2 {
	
	public  class InnerClass{
		public void a(){
			// 1. InnerClass must be static .else： if not No enclosing instance of type AccessTest1 is accessible.
			//即只有内部类为static，才可使用外部类AccessTest1.直接访问，类似于静态变量
			// 2. AccessTest1.InnerClass 必须为public或者default(package)或者明确标明protected，若其为private此处无法访问。
			//private 修改符修饰类时，只能作用于内部内
			AccessTest1.InnerClass a = new AccessTest1.InnerClass(); 
			
			PackageClass pc = new PackageClass();
		}
	}

}

