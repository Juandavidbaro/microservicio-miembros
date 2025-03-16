package com.icesi.microservicio_miembro;

import com.icesi.microservicio_miembro.model.Miembro;
import com.icesi.microservicio_miembro.repository.MiembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private MiembroRepository miembroRepository;

    @Override
    public void run(String... args) throws Exception {
        // Crear algunos miembros de ejemplo sin 'id'
        Miembro miembro1 = new Miembro("Juan Pérez", "juan.perez@example.com", LocalDate.of(2023, 1, 15));
        Miembro miembro2 = new Miembro("María González", "maria.gonzalez@example.com", LocalDate.of(2022, 5, 20));
        Miembro miembro3 = new Miembro("Carlos Ramírez", "carlos.ramirez@example.com", LocalDate.of(2021, 10, 10));

        // Guardar los miembros en la base de datos
        miembroRepository.saveAll(Arrays.asList(miembro1, miembro2, miembro3));

        System.out.println("Datos de miembros de ejemplo cargados exitosamente.");
    }
}
