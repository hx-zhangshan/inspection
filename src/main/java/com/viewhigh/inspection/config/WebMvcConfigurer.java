package com.viewhigh.inspection.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author zhangS
 * @Description
 * @date 2019-12-30 15:30
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {
    @Value(value = "${wxJPGs.path}")
    String wxJPG;
    @Value(value = "${wxJPGs_img.path}")
    String wxJPG_img;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //和页面有关的静态目录都放在项目的static目录下
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //添加 硬盘上的静态资源
        registry.addResourceHandler("/images/**").addResourceLocations(wxJPG_img);
        registry.addResourceHandler("/wxJPGs/**").addResourceLocations(wxJPG);
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}
