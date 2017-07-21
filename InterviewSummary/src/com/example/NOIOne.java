package com.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/**
 * http://ifeve.com/java-nio-scattergather/
 * 	rewind()方法
	Buffer.rewind()将position设回0，所以你可以重读Buffer中的所有数据。
	limit保持不变，仍然表示能从Buffer中读取多少个元素（byte、char等）
 * 
 * 
 * compare diffrent bufs
 * equals() method
	当满足下列条件时，表示两个Buffer相等：
		有相同的类型（byte、char、int等）。
		Buffer中剩余的byte、char等的个数相等。
		Buffer中所有剩余的byte、char等都相同。
		
	compareTo()方法
	compareTo()方法比较两个Buffer的剩余元素(byte、char等)，
	 如果满足下列条件，则认为一个Buffer“小于”另一个Buffer：
	第一个不相等的元素小于另一个Buffer中对应的元素 。
	所有元素都相等，但第一个Buffer比另一个先耗尽(第一个Buffer的元素个数比另一个少)
 * 
 *
 */
public class NOIOne {
	public static void main(String[] args) {
		try {
			//获取文件
			RandomAccessFile rf = new RandomAccessFile("f:/code/noi.txt", "rw");
			//使用已经打开的文件创建一个文件内容传输隧道
			FileChannel channel = rf.getChannel();
			//分配一个字节缓冲区
			ByteBuffer buf = ByteBuffer.allocate(48);
			//冲隧道中读取内容到缓冲区
			//another write method is put
			int readBuff = channel.read(buf);
			//循环读取内容，直到督导文件末尾
			while(readBuff != -1){
				//切换缓冲区的模式
				buf.flip();
				//循环读取当前缓冲区的内容
				while(buf.hasRemaining()){
					//read data has two methoed ,another methoed is used to 
					//write data to channel.for example channel.write(buf);
					System.out.println((char)buf.get());
				}
				//another methoed is compact,which is used to clear the content reaed
				buf.clear();
				//再次重隧道中读取
				readBuff = channel.read(buf);
			}
			//关闭隧道
			channel.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
