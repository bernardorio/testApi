package au.com.autogeneral.AutoGeneralTestAPI.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.not;
import static org.modelmapper.Conditions.isNotNull;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class Config {

    @Bean
    public Docket api() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Auto & General test API")
                .version("1.0")
                .build();

        return new Docket(SWAGGER_2)
                .apiInfo(apiInfo)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(not(regex("/error.*")))
                .build();
    }

    @Bean
    public ModelMapper mapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(isNotNull());
        return modelMapper;
    }
}
