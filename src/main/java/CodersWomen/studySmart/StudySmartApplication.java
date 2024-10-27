package CodersWomen.studySmart;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Study Smart API",
				version = "1.0",
				description = "API documentation for Study Smart Application"
		)
)
public class StudySmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudySmartApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new io.swagger.v3.oas.models.info.Info()
						.title("Study Smart API")
						.version("1.0")
						.description("Study Smart uygulaması için API dokümantasyonu")
						.contact(new Contact().name("CodersWomen").url("https://coderswomen.com").email("info@coderswomen.com"))
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
