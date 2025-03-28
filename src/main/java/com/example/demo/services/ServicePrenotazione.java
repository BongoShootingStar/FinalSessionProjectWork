package com.example.demo.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DaoPrenotazione;
import com.example.demo.entities.Prenotazione;

import lombok.Data;

@Service
@Data
public class ServicePrenotazione extends GenericService<Long, Prenotazione, DaoPrenotazione> {

    @Autowired
    private ServiceVisitaMedica visitaService;

    @Autowired
    private ServicePaziente pazienteService;

    @Override
    public Prenotazione construct(Map<String, String> params) {
        Prenotazione p = getContext().getBean(Prenotazione.class, params);
        if (params.containsKey("visita_id")) {
            p.setVisita(visitaService.findById(Long.valueOf(params.get("visita_id"))));
        }
        if (params.containsKey("paziente_id")) {
            p.setPaziente(pazienteService.findById(Long.valueOf(params.get("paziente_id"))));
        }
        return p;
    }
}