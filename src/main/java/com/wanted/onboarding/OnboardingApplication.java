package com.wanted.onboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
//@SpringBootApplication
public class OnboardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnboardingApplication.class, args);
	}

}
