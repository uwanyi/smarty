package com.uwanyi.lottery_draw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * created by jasonwang
 * on 2020/8/1 10:28
 * 接口文档自动生成配置类
 * localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("uwanyi swagger2")
                .description("uwanyi 项目接口文档")
                .termsOfServiceUrl("https://localhost:8080/")
                .version("1.0.0")
                .build();
    }


    @Bean
    public Docket createRestApi() {

        ResponseMessage responseMesssageSucc = new ResponseMessageBuilder()
                .code(0)
                .message("处理成功")
                .build();
        ResponseMessage responseMesssageFail = new ResponseMessageBuilder()
                .code(-1)
                .message("处理失败")
                .build();
        List<ResponseMessage> list = new ArrayList();
        list.add(responseMesssageSucc);
        list.add(responseMesssageFail);

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, list)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.uwanyi.lottery_draw.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
