package com.yuxian.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * send msg
 * @author zhengyuhan
 *
 */
public class Send extends Thread {
	
	private BufferedReader console;
	private DataOutputStream output;
	private boolean isRunning = true;
	
	public Send() {
		console = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public Send(Socket client) {
		this();
		try {
			output = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// if io exception happened, close all io
			isRunning = false;
			CloseUtil.closeAll(console, output);
			
		}
	}
	
	private String getMsg() {
		try {
			return console.readLine();
		} catch (IOException e) {
		}
		return "";
	}
	
	public void send() {
		String msg = getMsg();
		try {
			output.writeUTF(msg);
			output.flush(); 
		} catch (IOException e) {
			// if io exception happened, close all io
			isRunning = false;
			CloseUtil.closeAll(console, output);
		}
	}
	
	public void run(){
		while(isRunning) {
			send();
		}
	}
}
