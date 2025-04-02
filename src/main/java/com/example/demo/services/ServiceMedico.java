package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DaoMedico;
import com.example.demo.entities.GenericEntity;
import com.example.demo.entities.Medico;

import lombok.Data;

@Service
@Data
public class ServiceMedico extends GenericService<Long, Medico, DaoMedico> {

    @Override
    public Medico construct(Map<String, String> params) {
        return getContext().getBean(Medico.class, params);
    }

    public List<Medico> findBySpecializzazione(String specializzazione) {
        List<Medico> medici = new ArrayList<>();
        Map<Long, GenericEntity> tutti = getDao().findAll();
        for (GenericEntity entity : tutti.values()) {
            Medico m = (Medico) entity;
            if (m.getSpecializzazione() != null && m.getSpecializzazione().equalsIgnoreCase(specializzazione)) {
                medici.add(m);
            }
        }
        return medici;
    }

    public List<Medico> findByProfilo(String nome, String cognome, String specializzazione){
        List<Medico> medici = new ArrayList<>();
        Map<Long, GenericEntity> tutti = getDao().findAll();
        for (GenericEntity entity : tutti.values()) {
            Medico m = (Medico) entity;
            if (m.getNome() != null && m.getNome().equalsIgnoreCase(nome) && m.getCognome() != null && m.getCognome().equalsIgnoreCase(cognome) && m.getSpecializzazione() != null && m.getSpecializzazione().equalsIgnoreCase(specializzazione)) {
                medici.add(m);
            }
        }
        return medici;
    }

}
