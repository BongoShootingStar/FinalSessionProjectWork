package com.example.demo.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DaoVisitaMedica;
import com.example.demo.entities.VisitaMedica;

import lombok.Data;

@Service
@Data
public class ServiceVisitaMedica extends GenericService<Long, VisitaMedica, DaoVisitaMedica> {

    @Autowired
    private ServiceMedico serviceMedico;

    @Override
    public VisitaMedica construct(Map<String, String> params) {
        VisitaMedica v = getContext().getBean(VisitaMedica.class, params);
        if (params.containsKey("medico_id")) {
            v.setMedico(serviceMedico.findById(Long.parseLong(params.get("medico_id"))));
        }
        return v;
    }
}