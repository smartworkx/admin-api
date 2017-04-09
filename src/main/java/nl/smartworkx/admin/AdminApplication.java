package nl.smartworkx.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import nl.smartworkx.admin.model.bank.BankFileUploadEventHandler;
import nl.smartworkx.admin.model.bank.Mt940ImporterService;

@SpringBootApplication
public class AdminApplication {

	@Value("${cross-origin-addresses}")
	private String crossOriginAddresses;

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins(crossOriginAddresses);
			}
		};
	}

	@Bean
	BankFileUploadEventHandler bankFileUploadEventHandler(){
		return new BankFileUploadEventHandler();
	}


	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
}
