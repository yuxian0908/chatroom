package com.yuxian.socket;

import java.io.*;
import java.net.*;

/**
 * chat room client
 * @author zhengyuhan
 *
 */

public class MySocketClient extends Thread{
	
	private int port;
	private String addr;
	Socket client;
	private String name;
	
	public MySocketClient(String addr, int p) {
		this.port = p;
		this.addr = addr;
		client = new Socket(); 
		InetSocketAddress isa = new InetSocketAddress(addr,p);
		try {
			// get user name
			System.out.println("type in your name: ");
			BufferedReader nameInput = new BufferedReader(new InputStreamReader(System.in));
			this.name = nameInput.readLine();
			if(this.name.equals("")) return;
			
			
			client.connect(isa, 10000);
			
			Send send = new Send(client);
			send.send(this.name);
			send.start(); 
			
			Receive receive = new Receive(client);
			receive.start(); 
			
			
			// close socket
//			client.close();
//			client = null;
			
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
