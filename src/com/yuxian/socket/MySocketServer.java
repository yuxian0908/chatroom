package com.yuxian.socket;

import java.io.*;
import java.net.*;

/**
 * chat room server
 * @author zhengyuhan
 *
 */
public class MySocketServer extends Thread{
	
	private int port;
	private ServerSocket server;
	private boolean isRunning = true;
	
	public MySocketServer(int p){
		this.port = p;
		try {
			server = new ServerSocket(port);
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	public void run() {
		
		Socket client;
		try {
			client = server.accept();
		} catch (IOException e1) {
			isRunning = false;
			client = null;
		}
		while(isRunning) {
			try {
				
				// connect to client
//				synchronized(server) {
//					client = server.accept();
//				}
				
				// get input from client
				System.out.println("socket established...");
				DataInputStream input = new DataInputStream(client.getInputStream());
				String data = input.readUTF();
				System.out.println("value: "+data);
				
				// output msg to client
				DataOutputStream output = new DataOutputStream(client.getOutputStream());
				output.writeUTF("your msg: "+data);
				output.flush();
				
				
				// close socket
//				client.close();
				
			}catch(IOException e) {
				e.getStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new MySocketServer(8080);
		t1.start();
	}
	
}
