package ai.treeleaf.blog;

import ai.treeleaf.blog.configuration.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		 new SecurityProperties();
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration(){

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("ai.treeleaf"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails(){

		return new ApiInfo(
				"Treeleaf Blog",
				"Treeleaf Blog",
				"1.0",
				"Terms and conditions applied",
				new springfox.documentation.service.Contact("SILK Innovation","https://www.silkinnovation.com.np","info@silkinnovation.com.np"),
				"API License",
				"https://www.silkinnovation.com.np",
				Collections.emptyList());

	}

}
