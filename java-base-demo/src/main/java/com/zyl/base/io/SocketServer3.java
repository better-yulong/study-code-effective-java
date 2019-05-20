package com.zyl.base.io;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer3 {

	public static void main(String[] args) throws Exception {
		SocketServer3 socketServer =  new SocketServer3();
		socketServer.init();
	}
	
	public void init() throws Exception{
		ServerSocket serverSocket = new ServerSocket(9006,2);
		serverSocket.setReuseAddress(true);
		while(true){
			Socket client = serverSocket.accept();//点1
			InputStream in= client.getInputStream();
			byte[] inLen = null ;
			byte[] inByte = null ;
			for(;;){//点3
				inLen = new byte[4];
				int readCount = in.read(inLen);
				if(readCount == -1){
					break;//点3：开始错写成continue
				}
				int length = bytes2Int(inLen);
				inByte = new byte[length];
				in.read(inByte);
				String inData = new String(inByte,"UTF-8");
				System.out.println("length:" + length + ",String:" + inData);
			}
		}
	}
	
	private final static int bytes2Int(byte[] bytes){
         int num=bytes[3] & 0xFF;
         num |=((bytes[2] <<8)& 0xFF00);
         num |=((bytes[1] <<16)& 0xFF0000);
         num |=((bytes[0] <<24)& 0xFF0000);
         return num;
	}

}
/**
 * 
length:21,String:hello...1558335696449
length:21,String:hello...1558335696449
length:21,String:hello...1558335696449
..........
 * 
 * */

