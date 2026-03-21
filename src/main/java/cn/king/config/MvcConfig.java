package cn.king.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* 跨域配置 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // 往 Spring MVC 里注册跨域规则
    public void addCorsMappings(CorsRegistry registry) {
        // 对所有请求路径生效
        registry.addMapping("/**")
                // 允许任意来源的域名跨域访问
                .allowedOrigins("*")
                // .allowCredentials(true)
                // 允许前端请求携带任意请求头
                .allowedHeaders("*")
                // 只允许这几种 HTTP 方法跨域请求
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }

}
