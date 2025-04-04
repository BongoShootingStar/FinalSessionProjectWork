package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.database.DatabaseMySql;
import com.example.demo.database.IDatabase;

@Configuration
public class DaoContext {

    @Bean
    @Qualifier("databaseMySQL")
    public IDatabase databaseMySQL() {
        return new DatabaseMySql();
    }
}
