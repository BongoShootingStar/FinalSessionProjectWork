package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.database.DatabaseMySql;
import com.example.demo.entities.Utente;

import lombok.Data;

@Data
@Service
public abstract class DaoUtente {

    @Autowired
    private DatabaseMySql databaseMySQL;

    public Long createUtente(Utente e) {
        String comando = "INSERT INTO utenti (username, password, email, ruolo, nome, cognome, telefono, data_nascita) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return databaseMySQL.executeDML(comando, e.getUsername(), e.getPassword(), e.getEmail(), e.getRuolo(),
                e.getNome(), e.getCognome(), e.getTelefono(), String.valueOf(e.getData_nascita()));

    }

    public void updateUtente(Utente e) {
        String comando = "UPDATE utenti SET username = ?, password = ?, email = ?, ruolo = ?, nome = ?, cognome = ?, telefono = ?, data_nascita = ? WHERE id = ?";
        databaseMySQL.executeDML(comando, e.getUsername(), e.getPassword(), e.getEmail(), e.getRuolo(), e.getNome(),
                e.getCognome(), e.getTelefono(), String.valueOf(e.getData_nascita()), String.valueOf(e.getId()));
    }

    public void deleteUtente(Long id) {
        String comando = "DELETE FROM utenti WHERE id = ?";
        databaseMySQL.executeDML(comando, String.valueOf(id));
    }

    // public Utente findById(Long id) {
    // String query = "SELECT * FROM utenti WHERE id = ?";
    // Map<Long, Map<String, String>> risultato = databaseMySQL.executeDQL(query,
    // String.valueOf(id));
    // if (risultato.isEmpty()) {
    // return null;
    // }
    // Utente u = context.getBean(Utente.class, risultato.get(id));
    // return u;
    // }

    // public Map<Long, GenericEntity> findAll() {
    // String query = "SELECT * FROM utenti";
    // Map<Long, Map<String, String>> ris = databaseMySQL.executeDQL(query);
    // Map<Long, GenericEntity> map = new HashMap<>();
    // for (Entry<Long,Map<String,String>> coppia : ris.entrySet()) {
    // Utente u = context.getBean(Utente.class, coppia.getValue());
    // map.put(coppia.getKey(), u);
    // }
    // return map;
    // }

}
