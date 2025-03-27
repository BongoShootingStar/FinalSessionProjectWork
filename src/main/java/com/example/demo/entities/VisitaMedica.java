package com.example.demo.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class VisitaMedica extends GenericEntity{
    
    private LocalDate data;
    
    private LocalTime ora;
    
    private String esame;
    
    private String luogo;

    private double prezzo;
    
    private Medico medico;
}
