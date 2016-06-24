package nl.smartworkx.admin;

import java.time.Clock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class AdminApplication {

	@Value("${cross-origin-addresses}")
	private String crossOriginAddresses;

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				System.out.println("Cross origion address: " + crossOriginAddresses);
				registry.addMapping("/*").allowedOrigins(crossOriginAddresses);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
}
