package poly.edu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import poly.edu.interceptor.AuthInterceptor;
import poly.edu.interceptor.LogInterceptor;

@Configuration
public class LogInterceptorconfig implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;

    @Autowired
    LogInterceptor logInterceptor; // phải là LogInterceptor, không phải config

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 1. AuthInterceptor: bảo vệ các URL cần đăng nhập/admin
        registry.addInterceptor(authInterceptor)
            .addPathPatterns(
                "/admin/**",
                "/account/change-password",
                "/account/edit-profile",
                "/order/**")
            .excludePathPatterns(
                "/admin/home/index",
                "/auth/**",
                "/css/**", "/js/**", "/images/**");

        // 2. LogInterceptor: log tất cả các URL được bảo vệ
        registry.addInterceptor(logInterceptor)
            .addPathPatterns(
                "/admin/**",
                "/account/change-password",
                "/account/edit-profile",
                "/order/**");
    }
}
