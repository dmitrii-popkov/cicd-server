package ru.infotecs.cicd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CicdServerApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(CicdServerApplication.class, args);
		} catch (RuntimeException e) {
			log.error("Failed to start spring application!", e);
			throw e;
		}
	}
}
