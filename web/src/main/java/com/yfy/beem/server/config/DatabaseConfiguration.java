package com.yfy.beem.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <p>The configuration class for Hibernate database access. Instead of using, use database.properties for a more secure configuration that will not be shared on GitHub.</p>
 * // TODO: write javadoc
 * */
@Configuration
@PropertySource("classpath:database.properties")
public class DatabaseConfiguration {
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

//    @Bean
//    @Primary
//    public DataSource dataSource() {
//        return DataSourceBuilder
//                .create()
//                .url(url)
//                .username(username)
//                .password(password)
////                .driverClassName("org.hibernate.dialect.PostgreSQLDialect")
//                .build();
//    }
}
