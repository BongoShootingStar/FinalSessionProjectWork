package com.example.demo.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Medico extends Utente {

    private String specializzazione;
    
}
