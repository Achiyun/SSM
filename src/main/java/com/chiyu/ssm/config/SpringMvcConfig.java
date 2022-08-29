package com.chiyu.ssm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.time.format.DateTimeFormatter;

@Configuration
@ComponentScan("com.chiyu.ssm.controller")
@EnableAspectJAutoProxy
// 开启mvc的注解驱动
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateFormatter dateFormatter = new DateFormatter();
        dateFormatter.setFallbackPatterns("yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd",
                "yyyy/MM/dd HH:mm:ss",
                "yyyy-MM-dd'T'HH:mm",
                "yyyy/MM/dd"
        );
        registry.addFormatter(dateFormatter);

        final DateTimeFormatterRegistrar formatterRegistrar =
                new DateTimeFormatterRegistrar();
        formatterRegistrar.setTimeFormatter(DateTimeFormatter.ofPattern("HH:mm:ss"));

        formatterRegistrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        formatterRegistrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        formatterRegistrar.registerFormatters(registry);
    }

    // 创建一个多部分解析器
    // 配置文件上传
    @Bean
    public MultipartResolver multipartResolver() {
    /**
     * StandardServletMultipartResolver是基于servlet3.1原生上传组件封装的
     * 上传参数需要在web.xml或WebInit中配置
     */
        return new StandardServletMultipartResolver();
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // 放行静态资源
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 配置登陆视图的映射
        // 只需加载页面的时候写在这里
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/index.html").setViewName("index");
//        registry.addViewController("/dataList.html").setViewName("dataList");
    }

    /**
     * 模板解析器
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        // 开发阶段不开启缓存
        templateResolver.setCacheable(false);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setOrder(1);
        return templateResolver;
    }

    /**
     * 模板引擎
     */
    @Bean
    public SpringTemplateEngine springTemplateEngine(SpringResourceTemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    /**
     * 视图解析器
     */
    @Bean
    public ThymeleafViewResolver viewResolver(SpringTemplateEngine springTemplateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(springTemplateEngine);
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }


}
