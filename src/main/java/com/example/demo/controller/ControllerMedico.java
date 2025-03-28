package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Medico;
import com.example.demo.services.ServiceMedico;

@RestController
@RequestMapping("/medico")
public class ControllerMedico {

    @Autowired
    private ServiceMedico serviceMedico;

    @GetMapping
    public List<Medico> getAll() {
        return serviceMedico.findAll();
    }

    @GetMapping("/{id}")
    public Medico getById(@PathVariable Long id) {
        return serviceMedico.findById(id);
    }

    @PostMapping
    public Long create(@RequestBody Map<String, String> params) {
        Medico medico = serviceMedico.construct(params);
        return serviceMedico.getDao().create(medico);
    }

    @PutMapping
    public void update(@RequestBody Map<String, String> params) {
        serviceMedico.update(params);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        serviceMedico.delete(id);
    }

    @GetMapping("/specializzazione")
    public List<Medico> findBySpecializzazione(@RequestParam String specializzazione) {
        List<Medico> medici = new ArrayList<>();
        for (Object e : serviceMedico.getDao().findAll().values()) {
            Medico m = (Medico) e;
            if (m.getSpecializzazione() != null && m.getSpecializzazione().equalsIgnoreCase(specializzazione)) {
                medici.add(m);
            }
        }
        return medici;
    }
}
