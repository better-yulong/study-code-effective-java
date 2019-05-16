package com.test.jvm.demo1;

/**
 * 
 * Bytes 处理工具
 * @author zyl
 * 
 * */
public class ByteUtils {
	public static int byte2Int(byte[] b, int start , int len){
		int sum = 0 ;
		int end = start + len ;
		for(int i= start ; i<end ; i++){
			int n = ((int)b[i])&0xff;
			n<<=(--len)*8 ;
			sum = n + sum ;
		}
		return sum ;
	}
	
	public static byte[] int2Bytes(int value , int len){
		byte[] b = new byte[len];
		for(int i = 0; i<len ; i++){
			b[len-i-1] = (byte)((value >> 8*i) &0xff );
		}
		return b ;
	}
	
	public static String byte2String(byte[] b, int start , int len){
		return new String(b,start,len);
	}
	
	public static byte[] string2Bytes(String str){
		return str.getBytes();
	}
	
	
	/**
	 * byte[] 不好模拟，类似用字符串模块
	 * original:  123456789
	 * replace:666
	 * offset :4 ,len = 2
	 * 说明：将 123456789 第offset 位开始之后的 len 位用replace的前 len 来替换
	 * 结果：1234 66 789
	 * 
	 * **/
	
	public static byte[] byteReplace(byte[] originalBytes , int offset , int len , byte[] replaceBytes){
		byte[] newBytes = new byte[originalBytes.length + (replaceBytes.length - len)];
		
		System.arraycopy(originalBytes,0,newBytes,0,offset); //对应将originalBytes 前offset 复制到新的newBytes[]中
		System.arraycopy(replaceBytes, 0, newBytes, offset, replaceBytes.length); //对应将 replaceBytes 前 len 增量补充到 newBytes
		System.arraycopy(originalBytes, offset + len, newBytes, offset + replaceBytes.length,
				originalBytes.length - offset - len); //对应将 originalBytes 将被替换之后的内容增量补充到 nweBytes;
		
		return newBytes ;
	}
	
	
}
