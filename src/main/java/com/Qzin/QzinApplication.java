package com.Qzin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class QzinApplication {

	public static void main(String[] args) {
		SpringApplication.run(QzinApplication.class, args);
		log.info("--------- Starting Qzin Order Management Service ---------");
	}

}
