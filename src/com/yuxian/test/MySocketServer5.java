package com.yuxian.test;

import java.io.*;
import java.net.*;

//test socket chat server1
public class MySocketServer5 extends Thread{
	
	private int port;
	private ServerSocket server;
	
	public MySocketServer5(int p){
		this.port = p;
		try {
			server = new ServerSocket(port);
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	public void run() {
		
		Socket client;
		BufferedInputStream input;
		
		while(true) {
			try {
				synchronized(server) {
					client = server.accept();
				}
				System.out.println("socket established...");
//				client.setSoTimeout(15000);
				input = new BufferedInputStream(client.getInputStream());
				byte[] b = new byte[1024];
				String data = "";
				int length;
				while((length=input.read(b))>0) {
					data+=new String(b,0,length);
				}
				System.out.println("value: "+data);
				input.close();
				input = null;
				client.close();
				
			}catch(IOException e) {
				e.getStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new MySocketServer5(8080);
		t1.start();
	}
	
}
