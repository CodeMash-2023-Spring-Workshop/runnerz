package org.codemash.runnerz;

import org.codemash.runnerz.model.Location;
import org.codemash.runnerz.model.Run;
import org.codemash.runnerz.service.RunService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(RunService runService) {
		return (args) -> runService.create(new Run(null,"Sunday Morning Run", LocalDateTime.now(),LocalDateTime.now().plus(70, ChronoUnit.MINUTES),7, Location.OUTDOOR));
	}

}
