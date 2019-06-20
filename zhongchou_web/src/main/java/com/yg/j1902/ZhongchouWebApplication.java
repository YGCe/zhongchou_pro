package com.yg.j1902;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.yg.j1902.mapper")
public class ZhongchouWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZhongchouWebApplication.class, args);
	}

}
