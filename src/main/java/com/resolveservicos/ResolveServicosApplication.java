package com.resolveservicos;

import com.resolveservicos.utils.Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//exclude = {SecurityAutoConfiguration.class} // Disable Spring Security
@SpringBootApplication
public class ResolveServicosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResolveServicosApplication.class, args);
	}
}
