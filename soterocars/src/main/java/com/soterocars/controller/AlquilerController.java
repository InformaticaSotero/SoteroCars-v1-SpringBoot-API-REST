package com.soterocars.controller;

import com.soterocars.entity.Alquiler;
import com.soterocars.entity.Cliente;
import com.soterocars.entity.Sede;
import com.soterocars.entity.Vehiculo;
import com.soterocars.service.AlquilerService;
import com.soterocars.service.ClienteService;
import com.soterocars.service.SedeService;
import com.soterocars.service.VehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alquileres")
@Tag(name = "Alquileres", description = "API para la gestión de alquileres")
public class AlquilerController {

    @Autowired
    private AlquilerService alquilerService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private SedeService sedeService;

    @Operation(summary = "Listar todos los alquileres")
    @GetMapping
    public ResponseEntity<List<Alquiler>> listarAlquileres() {
        return ResponseEntity.ok(alquilerService.listarAlquileres());
    }

    @Operation(summary = "Obtener un alquiler por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Alquiler> obtenerAlquilerPorId(@PathVariable Long id) {
        Optional<Alquiler> alquiler = alquilerService.buscarAlquilerPorId(id);
        return alquiler.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar un nuevo alquiler")
    @PostMapping
    public ResponseEntity<Alquiler> registrarAlquiler(@RequestBody Alquiler alquiler) {
        if (alquiler.getVehiculo() != null && alquiler.getVehiculo().getId() != null) {
            Vehiculo vehiculo = vehiculoService.buscarVehiculoPorId(alquiler.getVehiculo().getId())
                    .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
            alquiler.setVehiculo(vehiculo);  // Establecer el vehículo ya persistido
        } else {
            throw new RuntimeException("Vehículo no proporcionado o no tiene ID.");
        }
        
        Alquiler alquilerGuardado = alquilerService.registrarAlquiler(alquiler);
        return ResponseEntity.ok(alquilerGuardado);
    }

    @Operation(summary = "Actualizar un alquiler por ID")
    @PutMapping("/{id}")
    public ResponseEntity<Alquiler> actualizarAlquiler(@PathVariable Long id, @RequestBody Alquiler alquiler) {
        Alquiler alquilerExistente = alquilerService.buscarAlquilerPorId(id)
                .orElseThrow(() -> new RuntimeException("Alquiler no encontrado"));

        if (alquiler.getFechaHoraRecogida() != null) {
            alquilerExistente.setFechaHoraRecogida(alquiler.getFechaHoraRecogida());
        }
        if (alquiler.getFechaHoraDevolucion() != null) {
            alquilerExistente.setFechaHoraDevolucion(alquiler.getFechaHoraDevolucion());
        }

        alquilerExistente.setCliente(alquiler.getCliente());
        alquilerExistente.setVehiculo(alquiler.getVehiculo());
        alquilerExistente.setSedeRecogida(alquiler.getSedeRecogida());
        alquilerExistente.setSedeDevolucion(alquiler.getSedeDevolucion());
        alquilerExistente.setEstadoAlquiler(alquiler.getEstadoAlquiler());

        Alquiler alquilerActualizado = alquilerService.actualizarAlquiler(alquilerExistente);
        return ResponseEntity.ok(alquilerActualizado);
    }

    @Operation(summary = "Eliminar un alquiler por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlquiler(@PathVariable Long id) {
        alquilerService.eliminarAlquiler(id);
        return ResponseEntity.noContent().build();
    }
  }
}
