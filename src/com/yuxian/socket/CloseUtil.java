package com.yuxian.socket;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtil {
	public static void closeAll(Closeable... io) {
		for(Closeable c:io) {
			try {
				c.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
