package cn.zekaio.api.config;

import cn.zekaio.api.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    // 添加中间件
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/session/login")
                .excludePathPatterns("/users/register");
    }

    // 允许跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("*")
                .maxAge(3600);
    }

}
