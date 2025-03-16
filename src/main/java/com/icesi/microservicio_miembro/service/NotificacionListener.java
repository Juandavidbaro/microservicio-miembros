package com.icesi.microservicio_miembro.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificacionListener {

    @RabbitListener(queues = "miembros-queue")
    public void recibirMensajeMiembros(String mensaje) {
        System.out.println("Mensaje recibido en miembros-queue: " + mensaje);
    }

    @RabbitListener(queues = "notificaciones-queue")
    public void recibirMensajeNotificaciones(String mensaje) {
        System.out.println("Mensaje recibido en notificaciones-queue: " + mensaje);

    }
}
