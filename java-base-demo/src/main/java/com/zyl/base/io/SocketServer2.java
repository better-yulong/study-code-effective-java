package com.zyl.base.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

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
				System.out.println(line);
				/*if("EOF".equals(line)){
					eof = true;
					break;
				}*/
				
			}
			/*if(eof){
				br.close();
				break;
			}*/
		}
		/*System.out.println("close");
		serverSocket.close();*/

	}

}

/***
 * hello...1557995541663hello...1557995541663hello...1557995541663EOF 
 */
