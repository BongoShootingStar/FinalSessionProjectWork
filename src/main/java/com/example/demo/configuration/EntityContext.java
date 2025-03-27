package com.example.demo.configuration;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.demo.entities.Utente;

@Configuration
public class EntityContext {
    
    @Bean
    @Scope("prototype")
    public Utente utente(Map<String, String> mappa) {
        Utente u = new Utente();
        u.fromMap(mappa);
        return u;
    }
}
