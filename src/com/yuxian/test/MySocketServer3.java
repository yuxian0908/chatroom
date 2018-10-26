package com.yuxian.test;

import java.net.*;
import java.io.*;

// test the difference between thread and runnable
public class MySocketServer3 extends Thread{
	private int port;
	private ServerSocket serv;
	private String name;
	
	public MySocketServer3(int p, String n) throws IOException{
		this.port = p;
		this.name = n;
		this.serv = new ServerSocket(p);
	}
	
	public void run(){
		Socket cli = null;
		try {
			while(true) {
				System.out.println("waiting...");
				cli = serv.accept();
				System.out.println("Hello "+this.name);
			}
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		Thread t1 = new MySocketServer3(8080,"Renee");
		t1.start();
		Thread t2 = new MySocketServer3(8088,"Yuxian");
		t2.start();
	}
}
