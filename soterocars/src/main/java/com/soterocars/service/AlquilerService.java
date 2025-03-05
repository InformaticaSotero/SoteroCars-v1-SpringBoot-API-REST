package com.soterocars.service;

import com.soterocars.entity.Alquiler;
import com.soterocars.entity.Vehiculo;
import com.soterocars.repository.AlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlquilerService {

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Autowired
    private VehiculoService vehiculoService;

    public List<Alquiler> listarAlquileres() {
        return alquilerRepository.findAll();
    }

    public Optional<Alquiler> buscarAlquilerPorId(Long id) {
        return alquilerRepository.findById(id);
    }

    public Alquiler registrarAlquiler(Alquiler alquiler) {
        Vehiculo vehiculo = alquiler.getVehiculo();

        if (vehiculo.getFechaBaja() != null) {
            throw new RuntimeException("El vehículo está dado de baja.");
        }

        // Lógica para verificar si el vehículo ya está alquilado en el rango de fechas
        for (Alquiler a : vehiculo.getAlquileres()) {
            if ((alquiler.getFechaHoraRecogida().isBefore(a.getFechaHoraDevolucion()) &&
                alquiler.getFechaHoraDevolucion().isAfter(a.getFechaHoraRecogida())) ||
                (alquiler.getFechaHoraRecogida().isEqual(a.getFechaHoraRecogida()) ||
                alquiler.getFechaHoraDevolucion().isEqual(a.getFechaHoraDevolucion()))) {
                throw new RuntimeException("El vehículo ya está alquilado en estas fechas.");
            }
        }

        Alquiler nuevoAlquiler = alquilerRepository.save(alquiler);
        
        // Asocia el alquiler al vehículo
        vehiculo.getAlquileres().add(nuevoAlquiler);
        
        // Pasa el ID del vehículo junto con el objeto actualizado
        vehiculoService.actualizarVehiculo(vehiculo.getId(), vehiculo);

        return nuevoAlquiler;
    }



    public Alquiler actualizarAlquiler(Alquiler alquiler) {
        if (alquiler.getCliente() == null || alquiler.getCliente().getId() == null) {
            throw new RuntimeException("Cliente no proporcionado.");
        }

        if (alquiler.getVehiculo() == null || alquiler.getVehiculo().getId() == null) {
            throw new RuntimeException("Vehículo no proporcionado.");
        }

        if (alquiler.getSedeRecogida() == null || alquiler.getSedeRecogida().getId() == null) {
            throw new RuntimeException("Sede de recogida no proporcionada.");
        }

        if (alquiler.getSedeDevolucion() == null || alquiler.getSedeDevolucion().getId() == null) {
            throw new RuntimeException("Sede de devolución no proporcionada.");
        }

        return alquilerRepository.save(alquiler);
    }


    public void eliminarAlquiler(Long id) {
        Alquiler alquiler = alquilerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Alquiler no encontrado")
        );

        Vehiculo vehiculo = alquiler.getVehiculo();

        // Remover el alquiler de la lista de alquileres del vehículo
        if (vehiculo != null) {
            vehiculo.getAlquileres().remove(alquiler);
            
            // Limpiar la referencia al vehículo en el alquiler
            alquiler.setVehiculo(null);  // Limpiar la referencia de vehículo
        }

        // Eliminar el alquiler de la base de datos
        alquilerRepository.deleteById(id);
    }


}
