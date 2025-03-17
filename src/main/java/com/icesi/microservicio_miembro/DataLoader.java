package com.icesi.microservicio_miembro;

import com.icesi.microservicio_miembro.model.Miembro;
import com.icesi.microservicio_miembro.repository.MiembroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(MiembroRepository miembroRepository) {
        return args -> {
            if (miembroRepository.count() == 0) { // Verificar si la BD ya tiene datos
                List<Miembro> miembros = List.of(
                        new Miembro(null, "Juan Pérez", "juan.perez@example.com", LocalDate.of(2023, 1, 15)),
                        new Miembro(null, "María González", "maria.gonzalez@example.com", LocalDate.of(2022, 5, 10)),
                        new Miembro(null, "Carlos Ramírez", "carlos.ramirez@example.com", LocalDate.of(2021, 8, 20))
                );
                miembroRepository.saveAll(miembros);
                System.out.println("Datos de miembros iniciales cargados");
            }
        };
    }
}