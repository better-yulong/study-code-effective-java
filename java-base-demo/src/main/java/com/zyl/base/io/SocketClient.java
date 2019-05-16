package com.zyl.base.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

	public static void main(String[] args) throws Exception, IOException {
		Socket socket = new Socket("127.0.0.7",8888);
		OutputStream out = socket.getOutputStream();
		out.write("hello...".getBytes());
		out.close();

	}

}
