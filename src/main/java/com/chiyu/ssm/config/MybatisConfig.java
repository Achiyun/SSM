package com.chiyu.ssm.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:jdbc.properties")
@MapperScan("com.chiyu.ssm.mapper")
public class MybatisConfig {

    @Value("${mysql.url}")
    private String url;
    @Value("${mysql.user}")
    private String username;
    @Value("${mysql.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 绑定数据库的连接池对象
        factoryBean.setDataSource(dataSource);
        // 配置插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        factoryBean.setPlugins(pageInterceptor);
        // 返回session工厂对象
        return factoryBean.getObject();
    }
}
