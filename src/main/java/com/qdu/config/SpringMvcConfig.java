package com.qdu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.FileNotFoundException;

@Configuration
@ComponentScan(basePackages = "com.qdu.controller")
public class SpringMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        try {
            registry.addResourceHandler("/static/img/**")
                    .addResourceLocations("file:" + ResourceUtils.getURL("classpath:static/img/").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
//        //configure uploading settings
//        resolver.setMaxUploadSize(3*1024*1024); //set max upload size as specified 3M
//        resolver.setDefaultEncoding("UTF-8"); //set request encoding
//        return resolver;
//    }
}
