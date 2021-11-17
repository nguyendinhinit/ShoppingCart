package com.rookies.nashtech.ShoppingCart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@RestController
public class SwaggerConfig {
//  public static final String AUTHORIZATION_HEADER = "Authorization";
//  public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
  public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String KEY_NAME = "JWT";
  public static final String PASS_AS = "header";

  @Value(value = "${spring.application.name}")
  private String name;

  @Value(value = "${spring.application.description}")
  private String description;

  @Value(value = "${spring.swagger.version}")
  private String version;

//  @Bean
//  public Docket apiV1() {
//    String version = "v1";
//    return new Docket(DocumentationType.SWAGGER_2)
//            .apiInfo(apiInfo())
//            .groupName(version)
//            .select()
//            .apis(RequestHandlerSelectors.any())
//            .paths(PathSelectors.regex(".*/" + version + "/.*"))
//            .build()
//            .securitySchemes(List.of(HttpAuthenticationScheme.BASIC_AUTH_BUILDER.name("basicAuth").description("Basic authorization").build()))
//            .securityContexts(List.of(securityContext()))
//            .securitySchemes(List.of(basicAuthScheme()));
//  }
//
//  private ApiInfo apiInfo() {
//    return new ApiInfoBuilder().title("Demo").description("Demo").contact(new Contact("Nguyen", "", "nguyendinhit319@gmail.com")).build();
//  }
//
//  private SecurityContext securityContext() {
//    return SecurityContext
//            .builder()
//            .securityReferences(List.of(basicAuthReference()))
//            .forPaths(PathSelectors.ant("/api/v1/**")).build();
//  }
//
//  private ApiKey apiKey() {
//    return new ApiKey("jwtToken", "Authorization", "header");
//  }
//
//  private SecurityScheme basicAuthScheme() {
//    return new BasicAuth("basicAuth");
//  }
//
//  private SecurityReference basicAuthReference() {
//    return new SecurityReference("basicAuth", new AuthorizationScope[0]);
//  }
//
//  @RequestMapping(value = "/api", method = RequestMethod.GET)
//  public ModelAndView redirect () {
//    return new ModelAndView("redirect:/swagger-ui.html");
//  }
//
//  @RequestMapping(value = "/api", method = RequestMethod.GET)
//  public ModelAndView redirectApi () {
//    return new ModelAndView("redirect:/swagger-ui.html");
//  }
//
//  @RequestMapping(value = "/doc", method = RequestMethod.GET)
//  public ModelAndView redirectDoc () {
//    return new ModelAndView("redirect:/swagger-ui.html");
//  }

  @Bean
  public Docket getDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .securityContexts(Collections.singletonList(securityContext()))
            .securitySchemes(Collections.singletonList(apiKey()))
            .groupName(version)
            .select()
            .apis(RequestHandlerSelectors.basePackage(name = "com.rookies.nashtech.ShoppingCart.controller"))
            .paths(PathSelectors.any())
            .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
            .title(name)
            .description(description)
            .build();
  }

  private ApiKey apiKey() {
    return new ApiKey(KEY_NAME, AUTHORIZATION_HEADER, PASS_AS);
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .build();
  }

  List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope
            = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView redirect() {
    return new ModelAndView("redirect:/swagger-ui.html");
  }

  @RequestMapping(value = "/api", method = RequestMethod.GET)
  public ModelAndView redirectApi() {
    return new ModelAndView("redirect:/swagger-ui.html");
  }

  @RequestMapping(value = "/doc", method = RequestMethod.GET)
  public ModelAndView redirectDoc() {
    return new ModelAndView("redirect:/swagger-ui.html");
  }
}
