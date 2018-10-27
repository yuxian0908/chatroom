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
		
		while(isRunning) {
			try {
				Socket client = server.accept();
				Thread tunnel = new MyTunnel(client);
				tunnel.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
	
	public static void main(String[] args) {
		Thread t1 = new MySocketServer(8080);
		t1.start();
	}
	
	private class MyTunnel extends Thread{
		
		private DataInputStream input;
		private DataOutputStream output;
		private boolean isRunning = true;
		
		public MyTunnel(Socket client) {
			try {
				input = new DataInputStream(client.getInputStream());
				output = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				isRunning = false;
				CloseUtil.closeAll(input,output);
			}
		}
		
		public void send(String msg) {
			if(msg==null||msg.equals("")) return;
			try {
				output.writeUTF("your msg: "+msg);
				output.flush();
			} catch (IOException e) {
				isRunning = false;
				CloseUtil.closeAll(output);
			}
		}
		
		public String receive() {
			try {
				String data = input.readUTF();
				return data;
			} catch (IOException e) {
				isRunning = false;
				CloseUtil.closeAll(input);
			}
			return "";
		}
		
		public void run(){
			while(isRunning) {
				send(receive());
			}
		}
	}
	
}
