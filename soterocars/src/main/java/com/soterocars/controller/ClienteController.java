package com.soterocars.controller;

import com.soterocars.entity.Cliente;
import com.soterocars.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "API para la gestión de clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Listar todos los clientes")
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @Operation(summary = "Obtener un cliente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarClientePorId(id);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar un nuevo cliente")
    @PostMapping
    public ResponseEntity<Cliente> registrarCliente(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.registrarCliente(cliente));
    }

    @Operation(summary = "Actualizar un cliente por ID")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, cliente));
    }

    @Operation(summary = "Eliminar un cliente por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
