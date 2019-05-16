package com.zyl.base.io;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class SocketClient2 {

	public static void main(String[] args) throws Exception, IOException {
		Socket socket = new Socket("127.0.0.1",9006);
		OutputStream out = socket.getOutputStream();
		BufferedOutputStream bop = new BufferedOutputStream(out);
		long times = System.currentTimeMillis();
		for(int i=0;i<300000;i++){
			if(i>0 && i%1000==0) Thread.sleep(30000l);
			System.out.println("hello..." + times);
		}
		bop.write(("EOF").getBytes());
		bop.close();
		socket.close();
	}

}
