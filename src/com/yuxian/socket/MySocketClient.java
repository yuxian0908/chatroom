package com.yuxian.socket;

import java.io.*;
import java.net.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

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
	Send send;
	Receive receive;
	
	public MySocketClient(String addr, int p) {
		this.port = p;
		this.addr = addr;
		client = new Socket(); 
		InetSocketAddress isa = new InetSocketAddress(addr,p);
		try {
			client.connect(isa, 10000);

			send = new Send(client);
			send.start(); 
			
			receive = new Receive(client);
			receive.start(); 
			
			
		}catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	public void setUserName(String name) {
		if(name.equals("")) return;
		this.name = name;
		System.out.println(this.name);
	}
	
	public String getUserName() {
		return this.name;
	}
	
	public void run() {
		
	}
	
	public static void main(String[] args) {
		Thread t1 = new MySocketClient("127.0.0.1",8080);
		t1.start();
	}
}
