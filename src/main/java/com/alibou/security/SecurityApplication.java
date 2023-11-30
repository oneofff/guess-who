package com.alibou.security;

import com.alibou.security.service.jwt.AuthenticationService;
import com.alibou.security.controller.auth.dto.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.alibou.security.model.user.Role.ADMIN;
import static com.alibou.security.model.user.Role.USER;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.username("admin")
					.password("admin")
					.role(ADMIN)
					.build();
			var user = RegisterRequest.builder()
					.username("user")
					.password("user")
					.role(USER)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());
			System.out.println("User token: " + service.register(user).getAccessToken());

		};
	}
}
