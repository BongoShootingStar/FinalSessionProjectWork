package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Prenotazione;
import com.example.demo.services.ServicePrenotazione;

@RestController
@RequestMapping("/prenotazione")
public class ControllerPrenotazione {

    @Autowired
    private ServicePrenotazione servicePrenotazione;

    @GetMapping
    public List<Prenotazione> getAll() {
        return servicePrenotazione.findAll();
    }

    @GetMapping("/{id}")
    public Prenotazione getById(@PathVariable Long id) {
        return servicePrenotazione.findById(id);
    }

    @PostMapping
    public Long create(@RequestBody Map<String, String> params) {
        Prenotazione prenotazione = servicePrenotazione.construct(params);
        return servicePrenotazione.getDao().create(prenotazione);
    }

    @PutMapping
    public void update(@RequestBody Map<String, String> params) {
        servicePrenotazione.update(params);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        servicePrenotazione.delete(id);
    }
}
