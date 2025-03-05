package com.soterocars.service;

import com.soterocars.entity.Cliente;
import com.soterocars.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente registrarCliente(@Valid Cliente cliente) {
        cliente.setFechaAlta(LocalDate.now());
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente actualizarCliente(Long id, @Valid Cliente clienteActualizado) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNombre(clienteActualizado.getNombre());
            cliente.setApellido1(clienteActualizado.getApellido1());
            cliente.setApellido2(clienteActualizado.getApellido2());
            cliente.setDni(clienteActualizado.getDni());
            cliente.setTelefono(clienteActualizado.getTelefono());
            cliente.setDomicilio(clienteActualizado.getDomicilio());
            cliente.setEmailContacto(clienteActualizado.getEmailContacto());
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}

