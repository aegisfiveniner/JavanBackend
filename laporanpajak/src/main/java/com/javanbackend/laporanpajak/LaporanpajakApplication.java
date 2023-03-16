package com.javanbackend.laporanpajak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LaporanpajakApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaporanpajakApplication.class, args);
	}

}
