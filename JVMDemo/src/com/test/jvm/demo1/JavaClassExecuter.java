package com.test.jvm.demo1;


/**
 * JavaClass 执行工具
 * ----- 未验证过
 * **/
public class JavaClassExecuter {
	
	/***
	 * 执行外部传过来的代表一个Java类的byte数组<br/>
	 * 将输入类的byte[]数组中代表java.lang.System的CONSTANT_Utf8_info 常量修改为劫持后的HackSystem类
	 * 执行方法为该类的static main(String[] args)方法，输出结果为该类向System.out/err输出信息
	 * @param classByte[] 代表一个Java类的byte[]数组
	 * @return 执行结果
	 */
	
	public static String execute(byte[] classByte){
		HackSystem.clearBufer();
		ClassModifier cm = new ClassModifier(classByte);
		byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "com/test/jvm/demo1/HackSystem");
		HotSwapClassLoader loader = new HotSwapClassLoader();
		return null;
	}

}
