package com.softeem.swagger;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2 // 开启swagger
public class MySwagger2Config extends WebMvcConfigurerAdapter {

    @Value("${swagger.is.enable:true}")
    private boolean swagger_is_enable;
    @Value("${swagger.base.package}")
    private String swaggerBasePackage;
    @Value("${swagger.path.mapping:/}")
    private String swaggerPathMapping;

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                // swagger 状态：true 启动, false 禁用
                .enable(swagger_is_enable)
                .apiInfo(apiInfo()).select()
                //扫描指定包中的swagger注解
                .apis(RequestHandlerSelectors.basePackage(swaggerBasePackage))
                .paths(PathSelectors.any())
                .build()
                .pathMapping(swaggerPathMapping)
                .globalOperationParameters(buildHeaderParam());
    }

    /**
     * 设置在swagger里面的公共参数，如果有需要重写此方法
     * 设置公共的参数。
     * 如：设置在请求头里面的token,userId等
     *
     * @return
     */
    protected List<Parameter> buildHeaderParam() {

        // demo：设置在请求头里面公共参数token

        /*List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());*/

        return null;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("基础平台 RESTful APIs")
                .description("基础平台 RESTful 风格的接口文档，内容详细，极大的减少了前后端的沟通成本，同时确保代码与文档保持高度一致，极大的减少维护文档的时间。")
                .version("1.0.0")
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
      //这个是为了swagger做的过滤


    }
}
