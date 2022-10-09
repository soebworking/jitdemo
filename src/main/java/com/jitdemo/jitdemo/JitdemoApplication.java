package com.jitdemo.jitdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class JitdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JitdemoApplication.class, args);
	}

}
