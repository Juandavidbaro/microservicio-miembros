package com.icesi.microservicio_miembro.repository;

import com.icesi.microservicio_miembro.model.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MiembroRepository extends JpaRepository<Miembro, Long> {
}

