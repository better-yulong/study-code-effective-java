package com.zyl.effective.demo;

import java.util.HashMap;
import java.util.Map;

public class ConstructorMulitTest {
	
	Map<String,Integer> map = new HashMap<>(); 
	
	ConstructorMulitTest(){
		
	}
	ConstructorMulitTest(int a,String b){
		
	}
	ConstructorMulitTest(String b,int a){
		
	}

}

/*
 * 通过javap 命令可发现虽然有三个构造函数，但实际class常量池只有一个Object类init对应的Methodref，
 * 而三个构造函数实际在字节码指令中均指向该符号引用
 * 
D:\work\workspace\work2\effective-demo\src\main\java\com\zyl\effective\demo>javap -s -v -p ConstructorMulitTest.class
Classfile /D:/work/workspace/work2/effective-demo/src/main/java/com/zyl/effective/demo/ConstructorMulitTest.class
  Last modified 2019-4-23; size 385 bytes
  MD5 checksum 474067a831b8aa43e87e157aaaa530c4
  Compiled from "ConstructorMulitTest.java"
public class com.zyl.effective.demo.ConstructorMulitTest
  SourceFile: "ConstructorMulitTest.java"
  minor version: 0
  major version: 51
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #3.#12         //  java/lang/Object."<init>":()V
   #2 = Class              #13            //  com/zyl/effective/demo/ConstructorMulitTest
   #3 = Class              #14            //  java/lang/Object
   #4 = Utf8               <init>
   #5 = Utf8               ()V
   #6 = Utf8               Code
   #7 = Utf8               LineNumberTable
   #8 = Utf8               (ILjava/lang/String;)V
   #9 = Utf8               (Ljava/lang/String;I)V
  #10 = Utf8               SourceFile
  #11 = Utf8               ConstructorMulitTest.java
  #12 = NameAndType        #4:#5          //  "<init>":()V
  #13 = Utf8               com/zyl/effective/demo/ConstructorMulitTest
  #14 = Utf8               java/lang/Object
{
  com.zyl.effective.demo.ConstructorMulitTest();
    Signature: ()V
    flags:
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 5: 0
        line 7: 4

  com.zyl.effective.demo.ConstructorMulitTest(int, java.lang.String);
    Signature: (ILjava/lang/String;)V
    flags:
    Code:
      stack=1, locals=3, args_size=3
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 8: 0
        line 10: 4

  com.zyl.effective.demo.ConstructorMulitTest(java.lang.String, int);
    Signature: (Ljava/lang/String;I)V
    flags:
    Code:
      stack=1, locals=3, args_size=3
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 11: 0
        line 13: 4
}
*/