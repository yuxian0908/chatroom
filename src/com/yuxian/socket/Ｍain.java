package com.yuxian.socket;

public class Ｍain {
	public static void main(String[] args) {
		Thread server = new MySocketServer(8080);
		Thread client1 = new MySocketClient("127.0.0.1",8080);
		Thread client2 = new MySocketClient("127.0.0.1",8080);
		Thread client3 = new MySocketClient("127.0.0.1",8080);
		server.start();
		client1.start();
		client2.start();
		client3.start();
		
	}
}
