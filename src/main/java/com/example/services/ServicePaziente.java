package com.example.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.dao.DaoPaziente;
import com.example.demo.entities.Paziente;

import lombok.Data;

@Service
@Data
public class ServicePaziente extends GenericService<Long, Paziente, DaoPaziente> {

    @Override
    public Paziente construct(Map<String, String> entita) {
        return getContext().getBean(Paziente.class, entita);
    }

}
