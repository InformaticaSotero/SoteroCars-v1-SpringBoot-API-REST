package com.soterocars.repository;

import com.soterocars.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
