package com.example.demo.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Prenotazione extends GenericEntity{
    
    private VisitaMedica visita;
    
    private Paziente paziente;
}
