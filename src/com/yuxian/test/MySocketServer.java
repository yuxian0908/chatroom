package com.yuxian.test;

//thread test
public class MySocketServer {
	public static void main(String[] args) {
		Thread T1 = new MyThread(1);
		Thread T2 = new MyThread(2);
		Thread T3 = new MyThread(3);
		Thread T4 = new MyThread(4);
		Thread T5 = new MyThread(5);
		T1.setPriority(1);
		T2.setPriority(2);
		T3.setPriority(3);
		T4.setPriority(4);
		T5.setPriority(5);
		T1.start();
		T2.start();
		T3.start();
		T4.start();
		T5.start();
		System.out.println("I am main");
	}
}

class MyThread extends Thread {
	
	private String count;
	
	public MyThread(int x) {
		this.count = String.valueOf(x);
	}
	
	public void run() {
		System.out.println("I am thread "+count);
	}
}
