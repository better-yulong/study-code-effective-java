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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
				iteratorKeys.remove();// fix-2
				if(selectionKey.isAcceptable()){
					 System.out.println("client accept....");
					ServerSocketChannel clientSocket = (ServerSocketChannel) selectionKey.channel();
					 SocketChannel clientSocketChannel = clientSocket.accept();
					 clientSocketChannel.configureBlocking(false);
					 clientSocketChannel.register(selector, SelectionKey.OP_READ);
				}else if(selectionKey.isReadable()){
					 System.out.println("client read....");
					SocketChannel clientSocketChannel = (SocketChannel) selectionKey.channel();
					readData(clientSocketChannel);
					clientSocketChannel.close();
				}
				//iteratorKeys.remove();// fix-1
			}
		}
	}

	private static  void readData(SocketChannel socketChannel) throws IOException{
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
		StringBuilder builder = null;
		List<Entry> list = new ArrayList<Entry>();
		
		while(true){
			byteBuffer.clear();
			int n = socketChannel.read(byteBuffer);
			if(n==-1){
				break;
			}
			byteBuffer.flip();
			int limit = byteBuffer.limit();
			int len_index = 0 ;
			int data_index = 0 ;
			int entry_index = 0 ;
			boolean lenFlag = true;
			
			for(int i=0 ; i<limit; i++){
				if(len_index<4&&lenFlag){
					if(len_index==0){
						Entry entry = new Entry();
						list.add(entry);
					}
					list.get(entry_index).getLenByte()[len_index]= byteBuffer.get(i);
					if(len_index==3){
						int len = bytes2Int(list.get(entry_index).getLenByte());
						list.get(entry_index).setLenInt(len);
						list.get(entry_index).initDataByte(len);
						lenFlag = false ;
						len_index = 0 ;
					}else{
						len_index++;
					}
					
				}else{
					if(data_index<list.get(entry_index).getDataByte().length){
						list.get(entry_index).getDataByte()[data_index] = (char) byteBuffer.get(i);
						if(data_index== list.get(entry_index).getDataByte().length-1){
							list.get(entry_index).setDataStr(String.valueOf(list.get(entry_index).getDataByte()));
							data_index = 0 ;
							entry_index++;
							lenFlag = true ;
						}else{
							data_index++;
						}
					}
						
				}
			}
			byteBuffer.clear();
		}
		for(Entry entry: list){
			builder = new StringBuilder();
			System.out.println(builder.append("length:").append(entry.lenInt).append(",String:").append(entry.dataStr));
		}
	}
	
	
	private final static int bytes2Int(byte[] bytes){
         int num=bytes[3] & 0xFF;
         num |=((bytes[2] <<8)& 0xFF00);
         num |=((bytes[1] <<16)& 0xFF0000);
         num |=((bytes[0] <<24)& 0xFF0000);
         return num;
	}
	
	private static class Entry{
		 byte[] lens = new byte[4];
		 char[] data;
		 
		 int lenInt = 0 ;
		 String dataStr ;
		 
		 
		 private byte[] getLenByte(){
			 return this.lens;
		 }
		 
		private char[] initDataByte(int len){
			data = new char[len];
			return data;
		}
		
		private char[] getDataByte(){
			return this.data;
		}
		
		private void setLenInt(int lenInt){
			this.lenInt = lenInt ;
		}
		
		private void setDataStr(String dataStr){
			this.dataStr = dataStr ;
		}
		
	}
}

/**
 * 运行结果：
client accept....
client read....
length:21,String:hello...1558607281166
length:21,String:hello...1558607281166
length:21,String:hello...1558607281166
length:21,String:hello...1558607281166
length:21,String:hello...1558607281166
 * 
 * */