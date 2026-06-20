package com.proyecto.core.notificacion.controller;

import com.proyecto.core.notificacion.application.dto.NotificacionEvento;
import com.proyecto.core.notificacion.messaging.producer.NotificacionPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestNotificacionController {

    private final NotificacionPublisher publisher;

    @PostMapping("/notify")
    public ResponseEntity<Map<String, Object>> sendTest(@RequestBody Map<String, Object> body) {
        NotificacionEvento ev = NotificacionEvento.builder()
                .usuarioId(body.get("usuarioId") != null ? Long.valueOf(body.get("usuarioId").toString()) : null)
                .titulo((String) body.getOrDefault("titulo", "Prueba"))
                .mensaje((String) body.getOrDefault("mensaje", "Mensaje de prueba"))
                .tipo((String) body.getOrDefault("tipo", "TEST"))
                .idMedicamento(body.get("idMedicamento") != null ? Long.valueOf(body.get("idMedicamento").toString()) : null)
                .nombreMedicamento((String) body.getOrDefault("nombreMedicamento", null))
                .stockMedicamento(body.get("stockMedicamento") != null ? Integer.valueOf(body.get("stockMedicamento").toString()) : null)
                .idSede(body.get("idSede") != null ? Long.valueOf(body.get("idSede").toString()) : null)
                .nombreSede((String) body.getOrDefault("nombreSede", null))
                .build();

        publisher.sendNotification(ev);
        return ResponseEntity.ok(Map.of("sent", true));
    }
}

