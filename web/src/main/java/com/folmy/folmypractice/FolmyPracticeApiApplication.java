package com.folmy.folmypractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = "com.folmy.folmypractice")
@EntityScan("com.folmy.folmypractice.model")
@EnableJpaRepositories("com.folmy.folmypractice.repository")
@EnableTransactionManagement
@EnableJpaAuditing
public class FolmyPracticeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FolmyPracticeApiApplication.class, args);
	}

}
