package com.icesi.microservicio_miembro.service;

import com.icesi.microservicio_miembro.model.Miembro;
import com.icesi.microservicio_miembro.repository.MiembroRepository;
import com.icesi.microservicio_miembro.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MiembroService {
    @Autowired
    private MiembroRepository miembroRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String MIEMBROS_TOPIC = "miembros-events";

    public Miembro registrarMiembro(Miembro miembro) {
        Miembro nuevoMiembro = miembroRepository.save(miembro);

        String rabbitMsg = "Nuevo miembro registrado: " + nuevoMiembro.getId();
        rabbitTemplate.convertAndSend("notificaciones-queue", rabbitMsg);

        String kafkaMsg = "Miembro agregado - ID: " + nuevoMiembro.getId() + ", Nombre: " + nuevoMiembro.getNombre();
        kafkaTemplate.send(MIEMBROS_TOPIC, kafkaMsg);

        return nuevoMiembro;
    }

    public List<Miembro> obtenerTodosMiembros() {
        return miembroRepository.findAll();
    }

    public Optional<Miembro> obtenerMiembroPorId(Long id) {
        return miembroRepository.findById(id);
    }

    public Miembro actualizarMiembro(Long id, Miembro miembroActualizado) {
        Optional<Miembro> existente = miembroRepository.findById(id);
        if (existente.isPresent()) {
            Miembro miembro = existente.get();
            miembro.setNombre(miembroActualizado.getNombre());
            miembro.setEmail(miembroActualizado.getEmail());

            String kafkaMsg = "Miembro actualizado - ID: " + miembro.getId() + ", Nombre: " + miembro.getNombre();
            kafkaTemplate.send(MIEMBROS_TOPIC, kafkaMsg);

            return miembroRepository.save(miembro);
        }
        throw new RuntimeException("Miembro no encontrado con id: " + id);
    }

    public void eliminarMiembro(Long id) {
        if (miembroRepository.existsById(id)) {
            miembroRepository.deleteById(id);

            String kafkaMsg = "Miembro eliminado - ID " + id;
            kafkaTemplate.send(MIEMBROS_TOPIC, kafkaMsg);
        } else {
            throw new RuntimeException("Miembro no encontrado con id: " + id);
        }
    }
}
