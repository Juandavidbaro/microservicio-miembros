package com.icesi.microservicio_miembro.service;

import com.icesi.microservicio_miembro.model.Miembro;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

    @RabbitListener(queues = "miembrosQueue")
    public void recibirMensaje(Miembro miembro) {
        System.out.println("Mensaje recibido desde RabbitMQ: " + miembro);
        // Aquí puedes procesar el miembro, como guardarlo en la base de datos
    }
}
