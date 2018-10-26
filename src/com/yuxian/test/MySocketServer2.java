package com.yuxian.test;

//socket server test
public class MySocketServer2 implements java.lang.Runnable{
	private int port;
	private java.net.ServerSocket serv;
	
	public MySocketServer2(int p) throws java.io.IOException{
		this.port = p;
		this.serv = new java.net.ServerSocket(p);
	}
	
	public void run() {
		java.net.Socket cli = null;
		while(true) {
			System.out.println("Waiting...");
			try {
				cli = this.serv.accept();
				System.out.println(cli.getInetAddress());
			}catch(java.io.IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws java.io.IOException{
		java.lang.Thread thread = new java.lang.Thread(new MySocketServer2(8080));
		thread.start();
	}
}
