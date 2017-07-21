package com.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/**
 * In the example,I only using the method is Scattering read 
 */
public class SGNIO {

	public static void main(String[] args) {
		//Scattering read==>equals to read the data within channel to many buffer(buffer > 2)
		try {
			//open the file
			RandomAccessFile rf = new RandomAccessFile("f:/code/noi.txt", "rw");
			//create FileChannel by the created file
			FileChannel channel = rf.getChannel();
			//create two buf
			ByteBuffer buf1 = ByteBuffer.allocate(5);
			ByteBuffer buf2 = ByteBuffer.allocate(5);
			//create a buffer array
			ByteBuffer [] buf = {buf1,buf2};
			//read data from channel to buf
			Long byteRead = channel.read(buf);
			//output these data
			while(byteRead != -1){
				//output these data of first buffer
				buf1.flip();//the mode is auto switch to write mode when program from the channel read data
				while(buf1.hasRemaining()){
					System.out.println((char)buf1.get());
				}
				//output the flag,which is used to flaging the buffer
				System.out.println("these data come from buffer1");
				//output these data of seconde buffer
				buf2.flip();
				while(buf2.hasRemaining()){
					System.out.println((char)buf2.get());
				}
				//output the flag,which is used to flaging the buffer
				System.out.println("these data come from buffer2");
				//clearing the twice buffer 
				buf1.clear();
				buf2.clear();
				//reding data from channel to buffer
				byteRead = channel.read(buf);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
