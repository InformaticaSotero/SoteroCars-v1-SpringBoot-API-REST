package com.soterocars.controller;

import com.soterocars.entity.Sede;
import com.soterocars.service.SedeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sedes")
@Tag(name = "Sedes", description = "API para la gestión de sedes")
public class SedeController {

    @Autowired
    private SedeService sedeService;

    @Operation(summary = "Listar todas las sedes")
    @GetMapping
    public ResponseEntity<List<Sede>> listarSedes() {
        return ResponseEntity.ok(sedeService.listarSedes());
    }

    @Operation(summary = "Obtener una sede por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Sede> obtenerSedePorId(@PathVariable Long id) {
        Optional<Sede> sede = sedeService.buscarSedePorId(id);
        return sede.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar una nueva sede")
    @PostMapping
    public ResponseEntity<Sede> registrarSede(@RequestBody Sede sede) {
        return ResponseEntity.ok(sedeService.registrarSede(sede));
    }

    @Operation(summary = "Actualizar una sede por ID")
    @PutMapping("/{id}")
    public ResponseEntity<Sede> actualizarSede(@PathVariable Long id, @RequestBody Sede sede) {
        return ResponseEntity.ok(sedeService.registrarSede(sede));
    }

    @Operation(summary = "Eliminar una sede por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSede(@PathVariable Long id) {
        sedeService.eliminarSede(id);
        return ResponseEntity.noContent().build();
    }
}
