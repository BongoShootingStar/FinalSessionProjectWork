package com.example.demo.configuration;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.demo.entities.Medico;
import com.example.demo.entities.Paziente;
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

    @Bean
    @Scope("prototype")
    public Paziente paziente(Map<String, String> mappa) {
        Paziente p = new Paziente();
        p.fromMap(mappa);
        return p;
    }

    @Bean
    @Scope("prototype")
    public Medico medico(Map<String, String> mappa) {
        Medico m = new Medico();
        m.fromMap(mappa);
        return m;
    }
}
