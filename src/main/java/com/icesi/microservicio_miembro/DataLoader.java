package com.icesi.microservicio_miembro;

import com.icesi.microservicio_miembro.model.Miembro;
import com.icesi.microservicio_miembro.repository.MiembroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private MiembroRepository miembroRepository;

    @Override
    public void run(String... args) throws Exception {
        // Cargar miembros de ejemplo
        Miembro miembro1 = new Miembro();
        miembro1.setNombre("Juan Pérez");
        miembro1.setEmail("juan.perez@example.com");
        miembro1.setFechaInscripcion(LocalDate.of(2024, 1, 10));
        miembroRepository.save(miembro1);

        Miembro miembro2 = new Miembro();
        miembro2.setNombre("María Gómez");
        miembro2.setEmail("maria.gomez@example.com");
        miembro2.setFechaInscripcion(LocalDate.of(2024, 2, 5));
        miembroRepository.save(miembro2);

        System.out.println("Datos de ejemplo de miembros cargados exitosamente.");
    }
}
