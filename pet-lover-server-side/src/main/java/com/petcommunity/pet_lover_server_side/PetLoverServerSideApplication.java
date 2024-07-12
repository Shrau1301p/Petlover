package com.petcommunity.pet_lover_server_side;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PetLoverServerSideApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetLoverServerSideApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer crosConfigurer(){
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedMethods("*")
					.allowedOrigins("http://localhost:4200");
			}
		};
	}
}
