package com.retailcommerce.category.util;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerDocumentationConfig {
    @Value("${swaggerdoc.groupName}")
    private String groupName;
    @Value("${swaggerdoc.title}")
    private String title;
    @Value("${swaggerdoc.description}")
    private String description;

    public SwaggerDocumentationConfig() {
    }

    @Bean
    @ConditionalOnProperty(
            name = {"swaggerdoc.tryout.disabled"},
            matchIfMissing = true,
            havingValue = ""
    )
    public UiConfiguration uiConfiguration() {
        return new UiConfiguration((String)null, new String[0]);
    }

    @Bean
    public Docket customImplementation() {
        return (new Docket(DocumentationType.SWAGGER_2)).securityContexts(Collections.singletonList(new SecurityContext(Collections.singletonList(SecurityReference.builder().reference("Authorization").scopes(new AuthorizationScope[0]).build()), (it) -> {
            return true;
        }))).securitySchemes(Collections.singletonList(new ApiKey("Authorization", "Authorization", "header"))).groupName(this.groupName).useDefaultResponseMessages(false).select().paths(Predicates.or(new Predicate[]{PathSelectors.regex("/c.*"), PathSelectors.regex("/info"), PathSelectors.regex("/health")})).build().apiInfo(this.apiInfo());
    }

    private ApiInfo apiInfo() {
        return (new ApiInfoBuilder()).title(this.title).description(this.description).build();
    }
}
