package com.soterocars.controller;

import com.soterocars.entity.Vehiculo;
import com.soterocars.service.VehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")  // Cambio aquí
@Tag(name = "Vehículos", description = "API para la gestión de vehículos disponibles")
public class InicioController {

    @Autowired
    private VehiculoService vehiculoService;

    @Operation(summary = "Listar vehículos disponibles con filtros opcionales de marca, fecha de recogida y fecha de devolución")
    @GetMapping("/disponibles")  // Cambio aquí
    public ResponseEntity<List<Vehiculo>> mostrarVehiculosDisponibles(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaRecogida,
            @RequestParam(required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaDevolucion) {

        List<Vehiculo> vehiculos;

        // Lógica para aplicar el filtro si los parámetros están presentes
        if (marca != null && fechaRecogida != null && fechaDevolucion != null) {
            vehiculos = vehiculoService.listarVehiculosFiltrados(marca, fechaRecogida, fechaDevolucion);
        } else {
            // Si no hay filtro, muestra todos los vehículos disponibles
            vehiculos = vehiculoService.listarVehiculosDisponibles();
        }

        return ResponseEntity.ok(vehiculos);
    }

    @Operation(summary = "Obtener todas las marcas disponibles de vehículos")
    @GetMapping("/marcas")
    public ResponseEntity<List<String>> obtenerMarcasDisponibles() {
        List<String> marcas = vehiculoService.obtenerMarcasDisponibles();
        return ResponseEntity.ok(marcas);
    }
}
