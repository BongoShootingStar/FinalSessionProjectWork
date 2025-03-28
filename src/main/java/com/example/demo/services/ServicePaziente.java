package com.example.demo.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DaoPaziente;
import com.example.demo.entities.Paziente;

import lombok.Data;

@Service
@Data
public class ServicePaziente extends GenericService<Long, Paziente, DaoPaziente> {
    
    @Override
    public Paziente construct(Map<String, String> params) {
        return getContext().getBean(Paziente.class, params);
    }

    public Long createPaziente(Map<String, String> params) {
        if (!params.containsKey("data_nascita")) {
            throw new IllegalArgumentException("Data di nascita obbligatoria");
        }
        Paziente p = construct(params);
        return getDao().create(p);
    }
}
