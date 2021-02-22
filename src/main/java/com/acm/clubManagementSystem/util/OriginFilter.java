package com.acm.clubManagementSystem.util;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @ClassName 跨域配置类
 * @Author lkh
 * @Date 2021/1/20 16:47
 * @Version 1.0
 */
@Configuration //配置类加上注解表明是一个配置类
public class OriginFilter {

    //注入一个corsFilter过滤器
    @Bean
    public CorsFilter corsFilter(){
        //实例化一个具体的CorsConfiguration
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许跨域的网页的域【实际过程中要写你自己的不同过的域名，注意这是在前后端的项目端口一致的情况下，如果不一致的话，还得加上端口名称，否则还会报跨域的错】
        corsConfiguration.addAllowedOrigin("http://localhost:9528");
        //允许携带cookie,如果要允许携带cookie的话，那么上边的允许跨域访问的域名就不能写*，如果是*的话，代表所有的域名都能跨域访问
        corsConfiguration.setAllowCredentials(true);
        //允许携带任何头信息
        corsConfiguration.addAllowedHeader("*");
        //允许所有的请求方法都可以跨域（get/put/post/delete）
        corsConfiguration.addAllowedMethod("*");
        //实例化一个具体的参数配置源对象（urlBaseCorsConfigurationSource是CorsConfigurationSource接口的一个实现类）
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        //校验所有的路径能不能跨域，使用/**
        configurationSource.registerCorsConfiguration("/**",corsConfiguration);
        //返回corsFilter实例，参数：cors配置对象,构造对象需要传参
        return new CorsFilter(configurationSource);
    };
}
