package com.wjb.newmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.wjb.newmall.dao")
public class NewmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewmallApplication.class, args);
	}

}
