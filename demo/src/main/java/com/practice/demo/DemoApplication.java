package com.practice.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class DemoApplication {
	private static final CountDownLatch latch = new CountDownLatch(1);
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		try {
			latch.await();  // Блокування, доки не завершиться застосунок
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
