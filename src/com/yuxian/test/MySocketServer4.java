package com.yuxian.test;


// test synchronize
public class MySocketServer4 extends Thread{
	private int number;
	
	public MySocketServer4(int n) {
		this.number = n;
	}
	
	public void run() {
		synchronized(MySocketServer4.class) {
			for(int i=0; i<5; i++) {
				System.out.println(this.number);
			}
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new MySocketServer4(1);
		Thread t2 = new MySocketServer4(2);
		Thread t3 = new MySocketServer4(3);
		t2.start();
		t3.start();
		t1.start();
	}
}
