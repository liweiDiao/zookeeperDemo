package com.dlw.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.dlw.demo")
public class ZookeeperDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZookeeperDemoApplication.class, args);
	}
}
