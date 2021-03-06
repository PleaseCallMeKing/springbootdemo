package cn.itweknow.sbswagger.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author ganchaoyang
 * @date 2019/3/1014:16
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.itweknow.sbswagger.controller"))
//                .paths(Predicates.or(PathSelectors.ant("/user/add"),
//                        PathSelectors.ant("/user/find/*"),
//                        PathSelectors.ant("/user/delete/*"),
//                        PathSelectors.ant("/user/update"),
//                        PathSelectors.ant("/user/pageQuery")
//                        ))
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, newArrayList(
                        new ResponseMessageBuilder()
                                .code(500)
                                .message("服务器发生异常")
                                .responseModel(new ModelRef("Error"))
                                .build(),
                        new ResponseMessageBuilder()
                                .code(403)
                                .message("资源不可用")
                                .build()
                ));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "在Spring Boot项目集成Swagger实例文档的基础上加beetl框架基本的增删改查",
                "",
                "API V1.0",
                "Terms of service",
                new Contact("", "", "wanglichong123@gmail.com"),
                "Apache", "http://www.apache.org/", Collections.emptyList());
    }
}
