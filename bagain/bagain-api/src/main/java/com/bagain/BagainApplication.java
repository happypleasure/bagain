package com.bagain;

import org.bagain.utils.SpringBeansUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value=SpringBeansUtils.class)
public class BagainApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(BagainApplication.class, args);
	}
}
