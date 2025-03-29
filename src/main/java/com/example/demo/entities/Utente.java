package com.example.demo.entities;

import java.time.LocalDate;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@ToString(callSuper = true)
@Slf4j
public class Utente extends GenericEntity {

    private String username;

    private String password;

    private String email;

    private String ruolo;

    private String nome;

    private String cognome;

    private String telefono;

    private LocalDate data_nascita;

}
