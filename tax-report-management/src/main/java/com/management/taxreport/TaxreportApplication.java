package com.management.taxreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TaxreportApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxreportApplication.class, args);
	}

}
