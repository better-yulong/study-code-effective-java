package com.test.jvm;

import sun.util.resources.LocaleData;
public class SunPackageCodeTest {

	public static void main(String[] args) {
		System.out.println("ce shi...");
	}
	//命令行编译会找不到 LocaleData 类，原因是jdk的代码保护,cun及其他部分包为jdk自身API，与平台相关不建议使用，故默认编译时若有该部分类
	//会不允许编译通过(ctSystem机制)，但可通过bootclasspath 指定rt.jar来允许编译

}
