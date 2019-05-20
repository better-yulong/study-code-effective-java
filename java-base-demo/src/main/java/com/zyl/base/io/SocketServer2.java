package com.zyl.base.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

/*解决粘包：通过/n分隔
 * 
 * **/
public class SocketServer2 {

	public static void main(String[] args) throws Exception {
		SocketServer2 socketServer =  new SocketServer2();
		socketServer.init();
	}
	
	public void init() throws Exception{
		ServerSocket serverSocket = new ServerSocket(9006,2);
		serverSocket.setReuseAddress(true);
		/*boolean eof = false ;*/
		while(true){
			Socket client = serverSocket.accept();
			InputStream in= client.getInputStream();
			Reader reader = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(reader);
			String line ;
			while((line=br.readLine())!=null){
				String[] lines = line.split("/n");
				for(String str:lines)
					System.out.println(str);
			}
		}
	}

}

/***
 * 运行结果：
hello...1558332560890
/n
hello...1558332560890
/n
hello...1558332560890
/n
hello...1558332560890
 */
