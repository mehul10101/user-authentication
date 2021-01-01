package com.project.uberAuthentication;

import com.project.uberAuthentication.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class UberAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberAuthenticationApplication.class, args);
	}

}
