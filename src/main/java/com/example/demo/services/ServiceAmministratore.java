package com.example.demo.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.dao.DaoAmministratore;
import com.example.demo.entities.Amministratore;

import lombok.Data;

@Service
@Data
public class ServiceAmministratore extends GenericService<Long, Amministratore, DaoAmministratore> {

    @Override
    public Amministratore construct(Map<String, String> entita) {
        return getContext().getBean(Amministratore.class, entita);
    }
    
}
