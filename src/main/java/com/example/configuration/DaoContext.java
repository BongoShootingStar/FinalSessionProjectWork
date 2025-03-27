package com.example.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.database.DatabaseMySQL;
import com.example.demo.database.IDatabase;

@Configuration
public class DaoContext {

    @Bean
    @Qualifier("databaseMySQL")
    public IDatabase databaseMySQL() {
        return new DatabaseMySQL();
    }
}
