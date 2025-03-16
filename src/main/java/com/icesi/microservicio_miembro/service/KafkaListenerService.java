package com.icesi.microservicio_miembro.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.icesi.microservicio_miembro.config.KafkaConfig;

@Service
public class KafkaListenerService {
    @KafkaListener(topics = KafkaConfig.MIEMBROS_TOPIC, groupId = "miembros-group")
    public void escucharEventosMiembros(String mensaje) {
        System.out.println("Evento Kafka recibido: " + mensaje);
    }
}