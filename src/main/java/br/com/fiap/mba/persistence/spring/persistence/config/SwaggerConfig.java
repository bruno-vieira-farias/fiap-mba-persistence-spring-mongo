package br.com.fiap.mba.persistence.spring.persistence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.fiap.mba.persistence.spring.persistence"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Projeto Persistence JPA")
                .description("Projeto para a disciplina Persistence do curso Fullstack Developer na FIAP. \nEste projeto foi construído utilizando Spring JPA.")
                .version("1.0.0")
                .build();
    }
}
