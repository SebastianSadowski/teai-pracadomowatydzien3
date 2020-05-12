package pl.sadowski.sba2tydz2carapi.Config;


import io.swagger.annotations.Api;
import net.bytebuddy.implementation.attribute.AnnotationAppender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.AntPathMatcher;
import pl.sadowski.sba2tydz2carapi.Controller.CarsController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@Profile(value = "DEV")
public class SwaggerConfig {

@Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/cars.*"))
                .build();
    }
}
