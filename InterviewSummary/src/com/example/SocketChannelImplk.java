package com.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 *in this class,i will implement the socketChannel 
 */
public class SocketChannelImplk {
	public static void main(String[] args) {
		try {
			//open a socketChannel
			SocketChannel socket = SocketChannel.open();
			//connect to server
			socket.connect(new InetSocketAddress("http://hgy.ngrok.cc/NIO/hello/world", 8080));
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
