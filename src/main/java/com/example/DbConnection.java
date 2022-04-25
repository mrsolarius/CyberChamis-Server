package com.example;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class DbConnection {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Bean
    public DataSource dataSource() throws SQLException {
        System.out.println("dataSource !!!");
        if (dbUrl == null || dbUrl.isEmpty()) {
            System.out.println("dbUrl is null or empty");
            return new HikariDataSource();
        } else {
            System.out.println("dbUrl is " + dbUrl);
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            config.setMaximumPoolSize(4);
            return new HikariDataSource(config);
        }
    }

}
