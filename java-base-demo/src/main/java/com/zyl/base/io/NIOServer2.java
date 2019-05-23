package com.zyl.base.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer2 {

	public static void main(String[] args) throws Exception {
		Selector  selector = Selector.open();
		
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		ssChannel.configureBlocking(false);
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		ServerSocket socket = ssChannel.socket();
		InetSocketAddress address = InetSocketAddress.createUnresolved("127.0.0.1", 8888);
		socket.bind(address);
		
		while(true){
			selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator= keys.iterator();
			while(keyIterator.hasNext()){
				SelectionKey key = keyIterator.next();
				if(key.isAcceptable()){
					ServerSocketChannel   ssChannel1 = (ServerSocketChannel) key.channel();
					SocketChannel sChannel = ssChannel.accept();
					sChannel.configureBlocking(false);
					sChannel.register(selector, SelectionKey.OP_READ);
				}else if(key.isReadable()){
					SocketChannel sChannel = (SocketChannel) key.channel();
					System.out.println("");
					sChannel.close();
				}
				keyIterator.remove();
			}
			
		}

	}
	
	private static String readDataFromSocketChannel(SocketChannel sChannel) throws Exception{
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		StringBuilder data = new StringBuilder();
		while(true){
			buffer.clear();
			int n = sChannel.read(buffer);
			if(n==-1){
				break;
			}
			
			buffer.flip();
			int limit = buffer.limit();
			char[] dst = new char[limit];
			for(int i=0 ; i<limit; i++){
				dst[i] = (char) buffer.get(i);
			}
			data.append(dst);
			buffer.clear();
		}
		return data.toString();
	}

}
