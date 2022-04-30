package com.cauchynote;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Cauchy
 */
@MapperScan("com.cauchynote.*.mapper")
@SpringBootApplication
public class CauchyNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CauchyNoteApplication.class, args);
	}

}
