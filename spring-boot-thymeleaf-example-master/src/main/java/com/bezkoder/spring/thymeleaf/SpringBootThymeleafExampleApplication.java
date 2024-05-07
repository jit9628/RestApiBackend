package com.bezkoder.spring.thymeleaf;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootThymeleafExampleApplication {

	public static void main(String[] args) throws UnknownHostException {
		
		 
		SpringApplication.run(SpringBootThymeleafExampleApplication.class, args);
		 
		  InetAddress localHost = InetAddress.getLocalHost();
		  System.out.println("LOCALHOST :"+localHost);
	}

}
