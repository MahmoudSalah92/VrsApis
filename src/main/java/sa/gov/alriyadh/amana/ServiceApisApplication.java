package sa.gov.alriyadh.amana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
//@EnableResourceServer
@EnableAsync
@OpenAPIDefinition(info=@Info(title="CommonApi Services Definition"))
public class ServiceApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApisApplication.class, args);
	}

}
