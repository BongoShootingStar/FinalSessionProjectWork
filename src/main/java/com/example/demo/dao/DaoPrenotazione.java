package com.example.demo.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.example.demo.database.DatabaseMySql;
import com.example.demo.entities.GenericEntity;
import com.example.demo.entities.Prenotazione;

import lombok.Data;

@Service
@Data
public class DaoPrenotazione implements IDao<Long, Prenotazione> {

    private final DatabaseMySql databaseMySQL;
    private final ApplicationContext context;

    @Override
    public Long create(Prenotazione e) {
        String comando = "INSERT INTO prenotazioni (id, visita_id, paziente_id) VALUES (?, ?, ?)";
        return databaseMySQL.executeDML(comando, String.valueOf(e.getId()), String.valueOf(e.getVisita().getId()),
                String.valueOf(e.getPaziente().getId()));
    }

    @Override
    public void update(Prenotazione e) {
        String comando = "UPDATE prenotazioni SET visita_id = ?, paziente_id = ? WHERE id = ?";
        databaseMySQL.executeDML(comando, String.valueOf(e.getVisita().getId()),
                String.valueOf(e.getPaziente().getId()),
                String.valueOf(e.getId()));
    }

    @Override
    public void delete(Long id) {
        String comando = "DELETE FROM prenotazioni WHERE id = ?";
        databaseMySQL.executeDML(comando, String.valueOf(id));
    }

    @Override
    public Prenotazione findById(Long id) {
        String query = "SELECT pre.*, v.*, p.* FROM prenotazioni pre INNER JOIN visitemediche v ON pre.visita_id = v.id INNER JOIN pazienti p ON pre.paziente_id = p.id WHERE pre.id = ?";
        Prenotazione p = new Prenotazione();
        Map<Long, Map<String, String>> result = databaseMySQL.executeDQL(query, String.valueOf(id));
        for (Map<String, String> entita : result.values()) {
            p = context.getBean(Prenotazione.class, entita);
        }
        return p;
    }

    @Override
    public Map<Long, GenericEntity> findAll() {
        String query = "SELECT pre.*, v.*, p.* FROM prenotazioni pre INNER JOIN visitemediche v ON pre.visita_id = v.id INNER JOIN pazienti p ON pre.paziente_id = p.id";
        Map<Long, Map<String, String>> result = databaseMySQL.executeDQL(query);
        Map<Long, GenericEntity> ris = new HashMap<>();
        GenericEntity p = null;
        for (Map<String, String> entita : result.values()) {
            p = context.getBean(Prenotazione.class, entita);
            ris.put(Long.parseLong(entita.get("id")), p);
        }
        return ris;
    }

}
