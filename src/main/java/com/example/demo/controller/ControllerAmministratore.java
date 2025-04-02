package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Amministratore;
import com.example.demo.services.ServiceAmministratore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/amministratore")
public class ControllerAmministratore {
    
    @Autowired
    private ServiceAmministratore serviceAmministratore;

    @GetMapping
    public List<Amministratore> getAll() {
        return serviceAmministratore.findAll();
    }
    
}
