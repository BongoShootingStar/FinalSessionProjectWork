package com.example.demo.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.database.DatabaseMySql;
import com.example.demo.entities.Amministratore;
import com.example.demo.entities.GenericEntity;
import com.example.demo.entities.Medico;
import com.example.demo.entities.Utente;

public class DaoAmministratore extends DaoUtente implements IDao<Long, Amministratore> {

    private final Utente utente;
    private final DatabaseMySql databaseMySQL;
    private final ApplicationContext context;
    
    @Transactional
    @Override
    public Long create(Amministratore e) {
        Long id = super.createUtente(e);
        String comando = "INSERT INTO amministratori (id, utente_id) VALUES (?, ?)";
        if (id != null) {
            e.setId(id);
            databaseMySQL.executeDML(comando, String.valueOf(e.getId()), String.valueOf(e.getId()));
        }
        return id;
    }

    @Override
    public void update(Amministratore e) {
        super.updateUtente(e);
        String comando = "UPDATE amministratori SET utente_id = ? WHERE id = ?";
        databaseMySQL.executeDML(comando, String.valueOf(e.getId()), String.valueOf(e.getId()));
    }

    @Override
    public void delete(Long id) {
        super.deleteUtente(id);
        String comando = "DELETE FROM amministratori WHERE id = ?";
        databaseMySQL.executeDML(comando, String.valueOf(id));
    }

    @Override
    public Amministratore findById(Long id) {
        String query = "SELECT a.*, u.* FROM amministratori a INNER JOIN utenti u ON a.utente_id = u.id WHERE a.id = ?";
        Map<Long, Map<String, String>> result = databaseMySQL.executeDQL(query, String.valueOf(id));
        if (result.size() == 0) {
            return null;
        }
        Amministratore m = context.getBean(Amministratore.class, result.get(id));
        return m;
    }

    @Override
    public Map<Long, GenericEntity> findAll() {
        String query = "SELECT a.*, u.* FROM amministratori a INNER JOIN utenti u ON a.utente_id = u.id";
        Map<Long, Map<String, String>> result = databaseMySQL.executeDQL(query);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Map.Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            Medico m = context.getBean(Medico.class, coppia.getValue());
            ris.put(coppia.getKey(), m);
        }
        return ris;
    }

}