package com.example.demo.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.example.demo.database.DatabaseMySQL;
import com.example.demo.entities.GenericEntity;
import com.example.demo.entities.VisitaMedica;

import lombok.Data;

@Service
@Data
public class DaoVisitaMedica implements IDao<Long, VisitaMedica> {

    private final DatabaseMySQL databaseMySQL;
    private final ApplicationContext context;

    @Override
    public Long create(VisitaMedica e) {
        String comando = "INSERT INTO visite_mediche (id, medico_id, esame, luogo, data, ora, prezzo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return databaseMySQL.executeDML(comando, String.valueOf(e.getId()), String.valueOf(e.getMedico().getId()),
                e.getEsame(), e.getLuogo(), String.valueOf(e.getData()), String.valueOf(e.getOra()),
                String.valueOf(e.getPrezzo()));
    }

    @Override
    public void update(VisitaMedica e) {
        String comando = "UPDATE visite_mediche SET medico_id = ?, esame = ?, luogo = ?, data = ?, ora = ?, prezzo = ? WHERE id = ?";
        databaseMySQL.executeDML(comando, String.valueOf(e.getMedico().getId()), e.getEsame(), e.getLuogo(),
                String.valueOf(e.getData()), String.valueOf(e.getOra()), String.valueOf(e.getPrezzo()),
                String.valueOf(e.getId()));
    }

    @Override
    public void delete(Long id) {
        String comando = "DELETE FROM visite_mediche WHERE id = ?";
        databaseMySQL.executeDML(comando, String.valueOf(id));
    }

    @Override
    public VisitaMedica findById(Long id) {
        String query = "SELECT v.*, m.*, u.* FROM visite_mediche v INNER JOIN medici m ON v.medico_id = m.id INNER JOIN utenti u ON m.utente_id = u.id WHERE v.id = ?";
        VisitaMedica v = null;
        Map<Long, Map<String, String>> result = databaseMySQL.executeDQL(query, String.valueOf(id));
        for (Map<String, String> entita : result.values()) {
            v = context.getBean(VisitaMedica.class, entita);
        }
        return v;
    }

    @Override
    public Map<Long, GenericEntity> findAll() {
        String query = "SELECT v.*, m.*, u.* FROM visite_mediche v INNER JOIN medici m ON v.medico_id = m.id INNER JOIN utenti u ON m.utente_id = u.id";
        Map<Long, Map<String, String>> result = databaseMySQL.executeDQL(query);
        Map<Long, GenericEntity> ris = new HashMap<>();
        GenericEntity v = null;
        for (Map<String, String> entita : result.values()) {
            v = context.getBean(VisitaMedica.class, entita);
            ris.put(Long.parseLong(entita.get("id")), v);
        }
        return ris;
    }

}
