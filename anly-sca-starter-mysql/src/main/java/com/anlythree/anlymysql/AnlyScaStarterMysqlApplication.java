package com.anlythree.anlymysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.anlythree.anlymysql.business.*.dao")
@SpringBootApplication
public class AnlyScaStarterMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnlyScaStarterMysqlApplication.class, args);
	}

}
