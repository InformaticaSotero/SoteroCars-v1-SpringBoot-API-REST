package com.soterocars.service;

import com.soterocars.entity.Sede;
import com.soterocars.repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SedeService {

    @Autowired
    private SedeRepository sedeRepository;

    // Listar todas las sedes
    public List<Sede> listarSedes() {
        return sedeRepository.findAll();
    }

    // Registrar una nueva sede
    public Sede registrarSede(Sede sede) {
        return sedeRepository.save(sede);
    }

    // Buscar una sede por ID
    public Optional<Sede> buscarSedePorId(Long id) {
        return sedeRepository.findById(id);
    }

    // Eliminar una sede por ID
    public void eliminarSede(Long id) {
        sedeRepository.deleteById(id);
    }
}
