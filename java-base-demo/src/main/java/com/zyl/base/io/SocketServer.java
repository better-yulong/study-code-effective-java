package com.zyl.base.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) throws Exception {
		SocketServer socketServer =  new SocketServer();
		socketServer.init();
	}
	
	public void init() throws Exception{
		ServerSocket serverSocket = new ServerSocket(9006,2);
		serverSocket.setReuseAddress(true);
		/*boolean eof = false ;*/
		while(true){
			Socket client = serverSocket.accept();
			try{
				
				InputStream in= client.getInputStream();
				Reader reader = new InputStreamReader(in);
				BufferedReader br = new BufferedReader(reader);
				String line ;
				while((line=br.readLine())!=null){
					System.out.println(line);
				}
			}catch(Exception e){
				System.out.println("client exception:" + client.hashCode());
		}
		}

	}

}

/***
 * hello...1557995541663hello...1557995541663hello...1557995541663EOF 
 */
