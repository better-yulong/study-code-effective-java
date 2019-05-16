package com.zyl.base.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class IOTest1 {

	public static void main(String[] args) throws Exception {
		
		
		//I/O
		URL url =new URL("https://www.baidu.com");
		InputStream  is = url.openStream();
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line;
		while((line=br.readLine())!=null){
			System.out.println(line);
		}
		
		System.out.println("--------------");
		//NIO-->
		fastCopy("E:\\src.txt","E:\\src1.txt");
		
	}
	
	private static void fastCopy(String src,String dist) throws Exception{
		FileInputStream fis = new FileInputStream(src);
		FileOutputStream fos = new FileOutputStream(dist);
		
		//创建ByteBuffer 缓冲对象实际有两种方式：
		//ByteBuffer.allocate 实际是new HeapByteBuffer，即在JVM堆上分配
		//ByteBuffer.allocateDirect 实际是new DirectByteBuffer,而其底层则是通过unsafe.allocateMemory在物理内存分配
		ByteBuffer bb = ByteBuffer.allocate(2048);
		FileChannel fcin = fis.getChannel();
		FileChannel fcout = fos.getChannel();
		
		while(true){
			int r = fcin.read(bb);
			if(r==-1) //read()返回-1 表示EOF 
				break;
			
			bb.flip();
			
			fcout.write(bb);
			
			bb.clear();
			
		}
		
	}

}
