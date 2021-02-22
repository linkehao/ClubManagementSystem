package com.acm.clubManagementSystem.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
// 开启Swagger2的自动配置
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docker(Environment environment){
        Parameter token = new ParameterBuilder().name("token")
                .description("用户登录令牌")
                .parameterType("header")
                .modelRef(new ModelRef("String"))
                .required(true)
                .build();
        List<Parameter> list=new ArrayList<>();
        list.add(token);
        boolean isEnable = environment.acceptsProfiles(Profiles.of("dev", "test"));
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .globalOperationParameters(list)
                .groupName("需要token认证")
                .enable(isEnable)
//                .ignoredParameterTypes(Integer.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.acm.clubManagementSystem.controller.authApi"))
                .paths(PathSelectors.ant("/**"))
                .build();
    }

    @Bean
    public Docket docker1(Environment environment){

        boolean isEnable = environment.acceptsProfiles(Profiles.of("dev", "test"));
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .groupName("无需token认证")
                .enable(isEnable)
                .ignoredParameterTypes(Integer.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.acm.clubManagementSystem.controller.publicApi"))
                .paths(PathSelectors.ant("/**"))
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("", "", "");
        return new ApiInfo("ACM俱乐部管理系统文档接口", // 标题
                "简单优雅的restful风格接口", // 描述
                "v1.0", // 版本
                "", // 组织链接
                contact, // 联系人信息
                "", // 许可
                "", // 许可连接
                new ArrayList<>()); // 扩展)
    }
}
