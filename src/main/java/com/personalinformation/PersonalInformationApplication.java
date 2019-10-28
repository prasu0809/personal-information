package com.personalinformation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.personalinformation.model.PersonalInformation;

@SpringBootApplication
@EnableJpaAuditing
public class PersonalInformationApplication {
	public static void main(String args[]) {
		
		SpringApplication.run(PersonalInformationApplication.class, args);
		
	}
}
