package dev.samet.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.KeyGenerator;
import java.util.Arrays;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

}
