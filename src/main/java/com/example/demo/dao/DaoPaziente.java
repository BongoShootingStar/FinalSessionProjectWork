package com.example.demo.dao;

import java.util.Map;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Paziente findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Map<Long, GenericEntity> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

}
