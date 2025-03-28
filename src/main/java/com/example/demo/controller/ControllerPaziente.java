package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Paziente;
import com.example.demo.services.ServicePaziente;

@RestController
@RequestMapping("/paziente")
public class ControllerPaziente {
    
    @Autowired
    private ServicePaziente servicePaziente;

    @GetMapping
    public List<Paziente> getAll() {
        return servicePaziente.findAll();
    }

    @GetMapping("/{id}")
    public Paziente getById(@PathVariable Long id) {
        return servicePaziente.findById(id);
    }

    @PostMapping
    public Long create(@RequestBody Map<String, String> params) {
        // Crea il Paziente tramite il bean in scope prototype
        Paziente paziente = servicePaziente.construct(params);
        return servicePaziente.getDao().create(paziente);
    }

    @PutMapping
    public void update(@RequestBody Map<String, String> params) {
        servicePaziente.update(params);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        servicePaziente.delete(id);
    }
}