package com.example.demo.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.example.demo.database.DatabaseMySQL;
import com.example.demo.entities.GenericEntity;
import com.example.demo.entities.Paziente;
import com.example.demo.entities.Utente;

import lombok.Data;

@Service
@Data
public class DaoPaziente extends DaoUtente implements IDao<Long, Paziente> {

    private final Utente utente;
    private final DatabaseMySQL databaseMySQL;
    private final ApplicationContext context;

    @Override
    public Long create(Paziente e) {
        Long id = super.createUtente(e);
        String comando = "INSERT INTO pazienti (id, utente_id) VALUES (?, ?)";
        if (id != null) {
            e.setId(id);
            databaseMySQL.executeDML(comando, String.valueOf(e.getId()), String.valueOf(e.getId()));
        }
        return id;
    }

    @Override
    public void update(Paziente e) {
        super.updateUtente(e);
        String comando = "UPDATE pazienti SET utente_id = ? WHERE id = ?";
        databaseMySQL.executeDML(comando, String.valueOf(e.getId()));
    }

    @Override
    public void delete(Long id) {
        super.deleteUtente(id);
        String comando = "DELETE FROM pazienti WHERE id = ?";
        databaseMySQL.executeDML(comando, String.valueOf(id));
    }

    @Override
    public Paziente findById(Long id) {
        String query = "SELECT * FROM pazienti WHERE id = ?";
        Map<Long, Map<String, String>> result = databaseMySQL.executeDQL(query, String.valueOf(id));
        if (result.size() == 0) {
            return null;
        }
        Paziente p = (Paziente) context.getBean("paziente");
        return p;
    }

    @Override
    public Map<Long, GenericEntity> findAll() {
        String query = "SELECT * FROM pazienti";
        Map<Long, Map<String, String>> result = databaseMySQL.executeDQL(query);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            Paziente p = context.getBean(Paziente.class, coppia.getValue());
            ris.put(coppia.getKey(), p);
        }
        return ris;
    }

}
