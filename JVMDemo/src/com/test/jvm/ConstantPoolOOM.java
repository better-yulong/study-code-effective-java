package com.test.jvm;

import java.util.ArrayList;
import java.util.List;
/**
 * require jdk:hotspot jdk 6 
 * java -XX:PermSize=10m -XX:MaxPermSize=10m com/test/jvm/ConstantPoolOOM
 * */

public class ConstantPoolOOM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i = 0;
		List<String> list = new ArrayList<String>();
		while(true){
			list.add(String.valueOf(i++).intern());
		}
	}

}
