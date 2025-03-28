package com.example.demo.entities;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@ToString(callSuper = true)
@Slf4j
public class Prenotazione extends GenericEntity{
    
    private VisitaMedica visita;
    
    private Paziente paziente;
}
