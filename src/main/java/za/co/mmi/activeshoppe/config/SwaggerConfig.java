package za.co.mmi.activeshoppe.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;

@EnableWebMvc
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${api.version}")
    private String version;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .produces(new HashSet<>(Arrays.asList("application/json")))
                .consumes(new HashSet<>(Arrays.asList("application/json")))
                .select()
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Active Shoppe", "MMI Active Shoppe REST API", version,
                "urn:tos", contact(),
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList());
    }

    private Contact contact() {
        return new Contact("MMI", "http://mmi.co.za", "activeshoppe@mmi.co.za");
    }
}
