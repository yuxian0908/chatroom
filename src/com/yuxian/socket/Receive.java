package com.yuxian.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive extends Thread {
	
	private DataInputStream input;
	private boolean isRunning = true; 
	
	public Receive() {
	}
	
	public Receive(Socket client) {
		try {
			input = new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			// if io exception happened, close all io
			isRunning = false;
			CloseUtil.closeAll(input);
		}
	}
	
	public String receive() {
		String msg = "";
		try {
			msg = input.readUTF();
		} catch (IOException e) {
			// if io exception happened, close all io
			isRunning = false;
			CloseUtil.closeAll(input);
		}
		return msg; 
	}
	
	public void run() {
		while(isRunning) {
			System.out.println(receive());
		}
	}
}
