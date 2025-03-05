package com.soterocars.controller;

import com.soterocars.entity.Vehiculo;
import com.soterocars.service.VehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehiculos")
@Tag(name = "Vehículos", description = "API para la gestión de vehículos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @Operation(summary = "Listar todos los vehículos")
    @GetMapping
    public ResponseEntity<List<Vehiculo>> listarVehiculos() {
        return ResponseEntity.ok(vehiculoService.listarVehiculos());
    }

    @Operation(summary = "Obtener un vehículo por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtenerVehiculoPorId(@PathVariable Long id) {
        Optional<Vehiculo> vehiculo = vehiculoService.buscarVehiculoPorId(id);
        return vehiculo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar un nuevo vehículo")
    @PostMapping
    public ResponseEntity<Vehiculo> registrarVehiculo(@RequestBody Vehiculo vehiculo) {
        return ResponseEntity.ok(vehiculoService.registrarVehiculo(vehiculo));
    }

    @Operation(summary = "Actualizar un vehículo por ID")
    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
        return ResponseEntity.ok(vehiculoService.actualizarVehiculo(id, vehiculo));
    }

    @Operation(summary = "Eliminar un vehículo por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVehiculo(@PathVariable Long id) {
        vehiculoService.eliminarVehiculo(id);
        return ResponseEntity.noContent().build();
    }
}
