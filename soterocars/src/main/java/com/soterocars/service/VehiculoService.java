package com.soterocars.service;

import com.soterocars.entity.Vehiculo;
import com.soterocars.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    // Registrar un vehículo
    public Vehiculo registrarVehiculo(Vehiculo vehiculo) {
        vehiculo.setFechaAdquisicion(LocalDate.now());
        return vehiculoRepository.save(vehiculo);
    }

    // Listar todos los vehículos
    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.findAll();
    }

    // Buscar un vehículo por su ID
    public Optional<Vehiculo> buscarVehiculoPorId(Long id) {
        return vehiculoRepository.findById(id);
    }

 // Actualizar un vehículo
    public Vehiculo actualizarVehiculo(Long id, Vehiculo vehiculoActualizado) {
        return vehiculoRepository.findById(id).map(vehiculo -> {
            vehiculo.setMatricula(vehiculoActualizado.getMatricula());
            vehiculo.setMarca(vehiculoActualizado.getMarca());
            vehiculo.setModelo(vehiculoActualizado.getModelo());
            
            // Solo actualizar fecha de adquisición si no es nula
            if (vehiculoActualizado.getFechaAdquisicion() != null) {
                vehiculo.setFechaAdquisicion(vehiculoActualizado.getFechaAdquisicion());
            }
            
            vehiculo.setFechaBaja(vehiculoActualizado.getFechaBaja());
            return vehiculoRepository.save(vehiculo);
        }).orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + id));
    }


    // Eliminar un vehículo
    public void eliminarVehiculo(Long id) {
        vehiculoRepository.deleteById(id);
    }

 // Listar vehículos disponibles (fechaBaja es null)
    public List<Vehiculo> listarVehiculosDisponibles() {
        return vehiculoRepository.findVehiculosDisponibles();
    }
    
    public List<Vehiculo> listarVehiculosFiltrados(String marca, LocalDateTime fechaRecogida, LocalDateTime fechaDevolucion) {
        return vehiculoRepository.findVehiculosFiltrados(marca, fechaRecogida, fechaDevolucion);
    }

    public List<String> obtenerMarcasDisponibles() {
        return vehiculoRepository.findDistinctByMarcaWhereFechaBajaIsNull();
    }
    

}
