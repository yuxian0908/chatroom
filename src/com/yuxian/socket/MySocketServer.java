package com.yuxian.socket;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * chat room server
 * @author zhengyuhan
 *
 */
public class MySocketServer extends Thread{
	
	private int port;
	private ServerSocket server;
	private boolean isRunning = true;
	private List<MyTunnel> all = new ArrayList<>();
	
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
				MyTunnel tunnel = new MyTunnel(client);
				all.add(tunnel);
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
		public String name;
		
		public MyTunnel(Socket client) {
			try {
				input = new DataInputStream(client.getInputStream());
				output = new DataOutputStream(client.getOutputStream());
				name = input.readUTF();
				systemlog(name+" joins");
			} catch (IOException e) {
				isRunning = false;
				CloseUtil.closeAll(input,output);
				leave();
			}
		}
		
		public void send(String msg) {
			if(msg==null||msg.equals("")) return;
			try {
				output.writeUTF(msg+"\n");
				output.flush();
			} catch (IOException e) {
				isRunning = false;
				CloseUtil.closeAll(output);
				leave();
			}
		}
		
		public void sendall(String msg) {
			if(msg.startsWith("@")&&msg.indexOf(":")>-1) {
				String someone = msg.substring(1,msg.indexOf(":"));
				String content = msg.substring(msg.indexOf(":")+1);
				sendto(someone, content);
			}else {
				sendothers(msg);
			}
		}
		
		public void sendto(String someone, String content) {
			for(MyTunnel t : all) {
				if(t.name.equals(someone)) t.send(this.name+ " told to you quietly: "+content);;
			}
		}
		
		public void sendothers(String msg) {
			for(MyTunnel t : all) {
				if(t==this) continue;
				t.send(this.name+": "+msg);
			}
		}
		
		public void systemlog(String msg) {
			for(MyTunnel t : all) {
				t.send("system: "+msg);
			}
		}
		
		public String receive() {
			try {
				String data = input.readUTF();
				return data;
			} catch (IOException e) {
				isRunning = false;
				CloseUtil.closeAll(input);
				leave();
			}
			return "";
		}
		
		public void leave() {
			all.remove(this);
			sendall(name+" leave...");
		}
		
		public void run(){
			while(isRunning) {
				sendall(receive());
			}
		}
	}
	
}
