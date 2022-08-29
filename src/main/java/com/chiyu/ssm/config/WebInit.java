package com.chiyu.ssm.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

// 代替web.xml的配置文件, web容器会自动加载
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    // 前端控制器的拦截路径
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        // 解决post请求的中文编码的问题
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");

        //添加一个隐藏域
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();

        // 有顺序要求
        return new Filter[]{encodingFilter, hiddenHttpMethodFilter};
    }

    // 设定自定义注册参数绑定
    // 配置文件上传的参数
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // 临时文件的路径, 创建文件上传时， 如果超过了指定的缓存临界点, 就会使用这个文件夹来缓存数据，文件上传后会自动清空
        String location = "/home/chenchiyu/classRoom/Tianlai03/temp";
        File file = new File(location);
        if (!file.exists() && !file.isDirectory()) {
            final boolean mkdir = file.mkdir();
        }
        // 上传参数
        // 上传的单个文件的最大值
        long maxFileSize = 1024 * 1024 * 40; //2M
        // 当次请求中所有文件的总大小的最大值
        long maxRequestSize = 1024 * 1024 * 80; //4M
        // 设置文件缓存的临界点,超过则先保存到临时目录
        int fileSizeThreshold = 0; //没有临界点, 就是不缓存
        //配置对multipart的支持
        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold);
        registration.setMultipartConfig(multipartConfigElement);
        // tomcat抛出异常时会被当成静态资源处理
        registration.setInitParameter("throwExceptionIfNoHandlerFound","true");
    }
}
