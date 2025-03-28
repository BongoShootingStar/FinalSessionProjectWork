package com.example.demo.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.database.DatabaseMySQL;
import com.example.demo.entities.GenericEntity;
import com.example.demo.entities.Medico;
import com.example.demo.entities.Utente;

import lombok.Data;

@Service
@Data
public class DaoMedico extends DaoUtente implements IDao<Long, Medico> {

    private final Utente utente;
    private final DatabaseMySQL databaseMySQL;
    private final ApplicationContext context;

    @Transactional
    @Override
    public Long create(Medico e) {
        Long id = super.createUtente(e);
        String comando = "INSERT INTO medici (id, utente_id, specializzazione) VALUES (?, ?, ?)";
        if (id != null) {
            e.setId(id);
            databaseMySQL.executeDML(comando, String.valueOf(e.getId()), String.valueOf(e.getId()),
                    e.getSpecializzazione());
        }
        return id;
    }

    @Override
    public void update(Medico e) {
        super.updateUtente(e);
        String comando = "UPDATE medici SET utente_id = ?, specializzazione = ? WHERE id = ?";
        databaseMySQL.executeDML(comando, String.valueOf(e.getId()), e.getSpecializzazione(), String.valueOf(e.getId()));
    }

    @Override
    public void delete(Long id) {
        super.deleteUtente(id);
        String comando = "DELETE FROM medici WHERE id = ?";
        databaseMySQL.executeDML(comando, String.valueOf(id));
    }

    @Override
    public Medico findById(Long id) {
        String query = "SELECT m.*, u.* FROM medici m INNER JOIN utenti u ON m.utente_id = u.id WHERE m.id = ?";
        Map<Long, Map<String, String>> result = databaseMySQL.executeDQL(query, String.valueOf(id));
        if (result.size() == 0) {
            return null;
        }
        Medico m = context.getBean(Medico.class, result.get(id));
        return m;

    }

    @Override
    public Map<Long, GenericEntity> findAll() {
        String query = "SELECT m.*, u.* FROM medici m INNER JOIN utenti u ON m.utente_id = u.id";
        Map<Long, Map<String, String>> result = databaseMySQL.executeDQL(query);
        Map<Long, GenericEntity> ris = new HashMap<>();
        for (Map.Entry<Long, Map<String, String>> coppia : result.entrySet()) {
            Medico m = context.getBean(Medico.class, coppia.getValue());
            ris.put(coppia.getKey(), m);
        }
        return ris;
    }

}
