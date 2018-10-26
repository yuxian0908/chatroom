package com.yuxian.socket;

import java.io.*;
import java.net.*;

public class MySocketClient extends Thread{
	
	private int port;
	private String addr;
	Socket client;
	
	public MySocketClient(String addr, int p) {
		this.port = p;
		this.addr = addr;
		client = new Socket(); 
		InetSocketAddress isa = new InetSocketAddress(addr,p);
		try {
			client.connect(isa, 10000);
			
			BufferedOutputStream output = new BufferedOutputStream(client.getOutputStream());
			output.write("This is client".getBytes());
			output.flush();
			output.close();
			output = null;
			client.close();
			client = null;
			
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	public void run() {
		
	}
	
	public static void main(String[] args) {
		Thread t1 = new MySocketClient("127.0.0.1",8080);
		t1.start();
		
	}
}
