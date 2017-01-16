package com.study.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 *
 * @author Jeffrey
 * @since 2017/01/16 10:47
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.study.spring.web"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("RESTful API")
        .description("Spring Boot中使用Swagger2构建RESTful APIs")
        .contact(new Contact("Jeffrey", "", "ji459705636@163.com"))
        .version("1.0")
        .build();
  }
}
