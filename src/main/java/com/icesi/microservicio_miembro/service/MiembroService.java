package com.icesi.microservicio_miembro.service;

import com.icesi.microservicio_miembro.model.Miembro;
import com.icesi.microservicio_miembro.repository.MiembroRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MiembroService {
    private final MiembroRepository miembroRepository;
    private final RabbitTemplate rabbitTemplate;

    public MiembroService(MiembroRepository miembroRepository, RabbitTemplate rabbitTemplate) {
        this.miembroRepository = miembroRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Miembro registrarMiembro(Miembro miembro) {
        Miembro nuevoMiembro = miembroRepository.save(miembro);
        String mensaje = "Nuevo miembro registrado: ID " + nuevoMiembro.getId();
        rabbitTemplate.convertAndSend("notificaciones-queue", mensaje);
        return nuevoMiembro;
    }

    public List<Miembro> obtenerTodosMiembros() {
        return miembroRepository.findAll();
    }

    public Optional<Miembro> obtenerMiembroPorId(Long id) {
        return miembroRepository.findById(id);
    }

    public Miembro actualizarMiembro(Long id, Miembro miembroActualizado) {
        return miembroRepository.findById(id).map(miembro -> {
            miembro.setNombre(miembroActualizado.getNombre());
            miembro.setEmail(miembroActualizado.getEmail());

            Miembro actualizado = miembroRepository.save(miembro);
            String mensaje = "Miembro actualizado: ID " + actualizado.getId();
            rabbitTemplate.convertAndSend("notificaciones-queue", mensaje);

            return actualizado;
        }).orElseThrow(() -> new IllegalArgumentException("Miembro no encontrado con ID: " + id));
    }

    public void eliminarMiembro(Long id) {
        if (miembroRepository.existsById(id)) {
            miembroRepository.deleteById(id);
            String mensaje = "Miembro eliminado: ID " + id;
            rabbitTemplate.convertAndSend("notificaciones-queue", mensaje);
        } else {
            throw new IllegalArgumentException("Miembro no encontrado con ID: " + id);
        }
    }
}
