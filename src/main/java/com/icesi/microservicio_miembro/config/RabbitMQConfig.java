package com.icesi.microservicio_miembro.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;


@Configuration
public class RabbitMQConfig {

    @Bean
    public FanoutExchange miembrosExchange() {
        return new FanoutExchange("miembros-exchange");
    }

    @Bean
    public Queue miembrosQueue() {
        return new Queue("miembros-queue", false);
    }
    @Bean
    public Queue notificacionesQueue() {
        return new Queue("notificaciones-queue", true); // Durable
    }

    @Bean
    public Binding binding(FanoutExchange miembrosExchange, Queue miembrosQueue) {
        return BindingBuilder.bind(miembrosQueue).to(miembrosExchange);
    }
}

