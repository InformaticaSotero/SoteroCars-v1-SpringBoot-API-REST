package com.soterocars.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	 
	@Column(name = "nombre")
	private String nombre;
	 
    @Column(name = "apellido1")
    private String apellido1;

    @Column(name = "apellido2")
    private String apellido2;

    @Column(name = "dni")
    private String dni;

    @Column(name = "domicilio")
    private String domicilio;

    @Column(name = "email_contacto")
    private String emailContacto;

    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "fecha_alta")
    private LocalDate fechaAlta;

    @Column(name = "fecha_baja")
    private LocalDate fechaBaja;

}
