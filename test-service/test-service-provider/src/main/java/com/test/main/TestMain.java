package com.test.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
	public static void main(String[] args){
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				 "classpath:spring/applicationContext.xml");

		context.start();
		System.out.println("-------------test服务dubbo服务启动成功---------------");
		synchronized (TestMain.class) {
			while (true) {
				try {
					TestMain.class.wait();
				} catch (Throwable e) {
				}
			}
		}
	}
}
