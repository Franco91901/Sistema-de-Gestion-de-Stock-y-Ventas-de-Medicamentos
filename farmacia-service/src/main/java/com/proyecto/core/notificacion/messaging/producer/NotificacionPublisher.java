package com.proyecto.core.notificacion.messaging.producer;

import com.proyecto.core.notificacion.application.dto.NotificacionEvento;
import com.proyecto.core.notificacion.messaging.config.RabbitMQConfig;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificacionPublisher {

    private final RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        log.info("Rabbit message converter: {}", rabbitTemplate.getMessageConverter().getClass().getName());
    }

    public void sendNotification(NotificacionEvento event) {
        try {
            log.info("Sending notification to RabbitMQ exchange={} routingKey={} tipo={} idSede={} idMedicamento={}",
                    RabbitMQConfig.EXCHANGE,
                    RabbitMQConfig.ROUTING_KEY,
                    event != null ? event.getTipo() : null,
                    event != null ? event.getIdSede() : null,
                    event != null ? event.getIdMedicamento() : null);
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE,
                    RabbitMQConfig.ROUTING_KEY,
                    event
            );
            log.info("Notification sent to RabbitMQ");
        } catch (AmqpException e) {
            log.error("RabbitMQ publish failed", e);
            throw new IllegalStateException(
                    "No se pudo enviar la notificacion por RabbitMQ. Revisa que el broker este activo y que exista el exchange/queue.",
                    e
            );
        }
    }
}
