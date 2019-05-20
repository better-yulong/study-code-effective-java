package com.zyl.base.io;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/*
 * 解决粘包：自定义协议  数据包 =  数据包长度+数据包内容
 * **/
public class SocketClient3 {

	public static void main(String[] args) throws Exception, IOException {
		Socket socket = new Socket("127.0.0.1",9006);
		OutputStream out = socket.getOutputStream();
		BufferedOutputStream bop = new BufferedOutputStream(out);
		long times = System.currentTimeMillis();
		for(int i=0;i<30;i++){
			if(i>0 && i%1000==0) Thread.sleep(30000l);
			String inStr = "hello..." + times ;
			byte[] inByte = inStr.getBytes("UTF-8");
			byte[] inLen = int2ByteArray(inByte.length);
			bop.write(inLen);
			bop.write(inByte);
			System.out.println(inStr);
		}
		bop.close();
		socket.close();
	}
	
	private final static byte[] int2ByteArray(int i){
         byte[] result=new byte[4];
         result[0]=(byte)((i >> 24)& 0xFF);
         result[1]=(byte)((i >> 16)& 0xFF);
         result[2]=(byte)((i >> 8)& 0xFF);
         result[3]=(byte)(i & 0xFF);
         return result;
     }

}
