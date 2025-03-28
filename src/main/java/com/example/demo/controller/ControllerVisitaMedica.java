package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.VisitaMedica;
import com.example.demo.services.ServiceVisitaMedica;

@RestController
@RequestMapping("/visitaMedica")
public class ControllerVisitaMedica {

    @Autowired
    private ServiceVisitaMedica serviceVisitaMedica;

    @GetMapping
    public List<VisitaMedica> getAll() {
        return serviceVisitaMedica.findAll();
    }

    @GetMapping("/{id}")
    public VisitaMedica getById(@PathVariable Long id) {
        return serviceVisitaMedica.findById(id);
    }

    @PostMapping
    public Long create(@RequestBody Map<String, String> params) {
        VisitaMedica visita = serviceVisitaMedica.construct(params);
        return serviceVisitaMedica.getDao().create(visita);
    }

    @PutMapping
    public void update(@RequestBody Map<String, String> params) {
        serviceVisitaMedica.update(params);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        serviceVisitaMedica.delete(id);
    }
}