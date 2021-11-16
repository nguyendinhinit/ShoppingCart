package com.rookies.nashtech.ShoppingCart.config;

import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

  @Bean
  public Docket apiV1() {
    String version = "v1";
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName(version).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.regex(".*/" + version + "/.*")).build().securitySchemes(Arrays.asList(HttpAuthenticationScheme.BASIC_AUTH_BUILDER.name("basicAuth").description("Basic authorization").build())).securityContexts(Arrays.asList(securityContext())).securitySchemes(Arrays.asList(basicAuthScheme()));
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Demo").description("Demo").contact(new Contact("Nguyen", "", "nguyendinhit319@gmail.com")).build();
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(Arrays.asList(basicAuthReference())).forPaths(PathSelectors.ant("/api/v1/**")).build();
  }

  private SecurityScheme basicAuthScheme() {
    return new BasicAuth("basicAuth");
  }

  private SecurityReference basicAuthReference() {
    return new SecurityReference("basicAuth", new AuthorizationScope[0]);
  }

  @RequestMapping(value = "/api", method = RequestMethod.GET)
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
