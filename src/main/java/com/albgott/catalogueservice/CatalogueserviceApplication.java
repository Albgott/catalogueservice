package com.albgott.catalogueservice;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.TimeZone;

@SpringBootApplication
@RefreshScope
public class CatalogueserviceApplication {

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
	}

	public static void main(String[] args) {
		SpringApplication.run(CatalogueserviceApplication.class, args);
	}

}
