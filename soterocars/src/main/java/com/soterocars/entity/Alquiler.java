package com.soterocars.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_sede_recogida", nullable = false)
    private Sede sedeRecogida;

    @ManyToOne
    @JoinColumn(name = "id_sede_devolucion")
    private Sede sedeDevolucion;

    private LocalDateTime fechaHoraRecogida;
    private LocalDateTime fechaHoraDevolucion;

    private String estadoAlquiler;

    @PrePersist
    @PreUpdate
    private void actualizarEstado() {
        LocalDateTime ahora = LocalDateTime.now();
        if (ahora.isBefore(fechaHoraRecogida)) {
            estadoAlquiler = "Pendiente";
        } else if (ahora.isAfter(fechaHoraRecogida) && ahora.isBefore(fechaHoraDevolucion)) {
            estadoAlquiler = "En curso";
        } else if (estadoAlquiler == null || estadoAlquiler.equals("En curso")) {
            estadoAlquiler = "No devuelto";
        }
    }
}
