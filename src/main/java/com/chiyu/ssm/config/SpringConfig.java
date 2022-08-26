package com.chiyu.ssm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"com.chiyu.ssm.service","com.chiyu.ssm.aspect"})
@Import(MybatisConfig.class)
// 开启AOP注解驱动
@EnableAspectJAutoProxy
public class SpringConfig {
}
