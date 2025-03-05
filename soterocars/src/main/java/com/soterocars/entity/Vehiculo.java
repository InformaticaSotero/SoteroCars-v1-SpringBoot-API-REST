package com.soterocars.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;

@Data
@Entity
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricula;
    private String marca;
    private String modelo;
    private LocalDate fechaAdquisicion;
    
    @Column(nullable = true)
    private LocalDate fechaBaja;
    
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<Alquiler> alquileres;

    @Transient
    public String getEstadoVehiculo() {
        if (fechaBaja != null) {
            return "De baja";
        }

        // Verificar si el vehículo tiene alquileres activos
        for (Alquiler alquiler : alquileres) {
            LocalDateTime ahora = LocalDateTime.now();
            
            // Comprobar si el alquiler está en curso
            if ("Pendiente".equals(alquiler.getEstadoAlquiler())) {
                return "Reservado";
            }

            // Comprobar si el alquiler está activo (es decir, el vehículo está alquilado)
            if (ahora.isAfter(alquiler.getFechaHoraRecogida()) && ahora.isBefore(alquiler.getFechaHoraDevolucion())) {
                return "Alquilado";
            }

            // Si el alquiler está devuelto
            if ("Devuelto".equals(alquiler.getEstadoAlquiler())) {
                continue; // Si está devuelto, seguimos con el siguiente alquiler
            }
        }

        return "Disponible";  // Si no hay alquileres activos, el vehículo está disponible
    }

}
