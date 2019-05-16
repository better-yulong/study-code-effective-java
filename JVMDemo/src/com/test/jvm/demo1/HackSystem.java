package com.test.jvm.demo1;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 *  为JavaClass 劫持 java.lang.System 提供劫持
 *  除了 out、err 外，其余的直接转发给System 处理
 *  
 *  @author zyl
 * */
public class HackSystem{
	
	public final static InputStream in = System.in ;
	
	public final static ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	
	
	public final static PrintStream out = new PrintStream(buffer);
	
	public final static PrintStream err = out ;
	
	public static String getBufferString(){
		return buffer.toString();
	}
	
	public static void clearBufer(){
		buffer.reset();
	}
	
	public static void setSecurityManager(final SecurityManager s){
		System.setSecurityManager(s);
	}
	
	public static SecurityManager getSecurityManager(){
		return System.getSecurityManager();
	}
	
	public static long currentTimeMills(){
		return System.currentTimeMillis();
	}
	
	public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length){
		System.arraycopy(src, srcPos, dest, destPos, length);
	}
	
	public static int identityHashCode(Object x){
		return System.identityHashCode(x);
	}

}
