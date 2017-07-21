package com.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
/**
 *in this class,I has implemented the function,which is to copy content from 
 *a file to another file 
 */
public class TransferData {

	public static void main(String[] args) {
		
		try {
			//opening  first file and create a channel
			RandomAccessFile rfFrom = new RandomAccessFile("f:/code/noi.txt", "rw");
			FileChannel channelFrom = rfFrom.getChannel();
			//open seconde file and create a channel
			RandomAccessFile rfTo = new RandomAccessFile("f:/code/noito.txt", "rw");
			FileChannel channelTo =rfTo.getChannel();
			//using the method to copy content from rfFrom channel to rfTo Channel
			channelTo.transferFrom(channelFrom, 0, channelFrom.size());
			//closing these channel opened
			channelFrom.close();
			channelTo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}

}
