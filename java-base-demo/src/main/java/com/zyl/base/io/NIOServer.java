package com.zyl.base.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/***
 * NIO必须由Selector和Channel（ServerSocketChannel、SocketChannel）配合使用
 * jdk class api: https://docs.oracle.com/javase/7/docs/api/allclasses-noframe.html
 * 三个重要概念：
 * 	 Selector ： https://docs.oracle.com/javase/7/docs/api/java/nio/channels/Selector.html
 * 	 ServerSocket：
 * 	 SelectionKey : https://docs.oracle.com/javase/7/docs/api/java/nio/channels/SelectionKey.html
 * 
 * */
public class NIOServer {

	public static void main(String[] args) throws Exception {
		Selector selector= Selector.open();
		//Selector selector2= SelectorProvider.provider().openSelector();
		
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT); 
		
		ServerSocket serverSocket = serverSocketChannel.socket();
		SocketAddress socketAddress = new InetSocketAddress(9006);
		serverSocket.bind(socketAddress);
		while(true){
			selector.select(); //没有客户端socket请求进来之前会阻塞
			//此行错误，具体见selector的keys、selectKeys方法返回结果集  https://www.cnblogs.com/xianyijun/p/5422727.html
			//Set<SelectionKey> selectionKeys = selector.keys(); ////fix-3 , debug返回示例为： Collections$UnmodifiableSet<E> 
			Set<SelectionKey> selectionKeys = selector.selectedKeys(); //fix-3 , debug返回示例为： HashSet
			Iterator<SelectionKey> iteratorKeys = selectionKeys.iterator();
			while(iteratorKeys.hasNext()){
				SelectionKey selectionKey = iteratorKeys.next();
				//iteratorKeys.remove();// fix-2
				if(selectionKey.isAcceptable()){
					 System.out.println("client accept....");
					ServerSocketChannel clientSocket = (ServerSocketChannel) selectionKey.channel();
					 SocketChannel clientSocketChannel = clientSocket.accept();
					 clientSocketChannel.configureBlocking(false);
					 clientSocketChannel.register(selector, SelectionKey.OP_READ);
				}else if(selectionKey.isReadable()){
					 System.out.println("client read....");
					SocketChannel clientSocketChannel = (SocketChannel) selectionKey.channel();
					System.out.println(readData(clientSocketChannel));
					clientSocketChannel.close();
				}
				//iteratorKeys.remove();// fix-1
			}
		}
	}

	private static  String readData(SocketChannel socketChannel) throws IOException{
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
		StringBuilder builder = new StringBuilder();
		while(true){
			byteBuffer.clear();
			int n = socketChannel.read(byteBuffer);
			if(n==-1){
				break;
			}
			byteBuffer.flip();
			int limit = byteBuffer.limit();
			char[] dst = new char[limit];
			for(int i=0 ; i<limit; i++){
				dst[i] = (char) byteBuffer.get(i);
			}
			builder.append(dst);
			byteBuffer.clear();
		}
		return builder.toString();
	}
}
