package com.soterocars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.soterocars.entity.Vehiculo;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    // Buscar vehículos con fecha de baja null
	@Query("SELECT v FROM Vehiculo v WHERE v.fechaBaja IS NULL")
    List<Vehiculo> findVehiculosDisponibles();
    
	@Query("SELECT v FROM Vehiculo v WHERE v.fechaBaja IS NULL " +
		       "AND (NOT EXISTS (SELECT 1 FROM v.alquileres a " +
		       "WHERE (a.fechaHoraRecogida BETWEEN :fechaRecogida AND :fechaDevolucion) " +
		       "OR (a.fechaHoraDevolucion BETWEEN :fechaRecogida AND :fechaDevolucion) " +
		       "OR (a.fechaHoraRecogida <= :fechaRecogida AND a.fechaHoraDevolucion >= :fechaDevolucion))) " +
		       "AND (v.marca LIKE %:marca%)")
		List<Vehiculo> findVehiculosFiltrados(String marca, LocalDateTime fechaRecogida, LocalDateTime fechaDevolucion);


    
    @Query("SELECT DISTINCT v.marca FROM Vehiculo v WHERE v.fechaBaja IS NULL")
    List<String> findDistinctByMarcaWhereFechaBajaIsNull();

}