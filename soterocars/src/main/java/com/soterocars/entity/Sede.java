package com.soterocars.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String domicilio;
    private String telefono;
    private String direccionWeb;
    private String email;

    private LocalDate fechaApertura;
    private String horario;
}

