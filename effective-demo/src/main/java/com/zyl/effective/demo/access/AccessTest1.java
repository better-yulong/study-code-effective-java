package com.zyl.effective.demo.access;


//顶层（非嵌套的）的类，只允许包级私有(package-private)和公共的(public）
public class AccessTest1 { 
	
	 //内部类
	 //can private/protected/public or default(package), if private ,just be used in this file.
	/*private*/ static class InnerClass{ 
		public void a(){
		}
	}

}

//can't private/protected/public,only default(package)--只允许默认包级私有
class PackageClass{ 
	private void test(){

	}
}
