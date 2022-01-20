package com.coderhouse.controller;

import com.coderhouse.handle.ApiRestException;
import com.coderhouse.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/coder-house")
@Slf4j
public class MessageController {

    @GetMapping("/mensajes/example")
    public String getMensajesString() {
        log.info("GET Request recibido string");
        return "Ejemplo de respuesta";
    }

    @GetMapping("/mensajes/all")
    public List<Message> getMensajesAll() {
        log.info("GET Request recibido string");
        return dataMensajes();
    }

    @GetMapping("/mensajes")
    public List<Message> getMensajesByDescription(@RequestParam String description) {
        log.info("GET obtener mensajes que sean iguales a la descripción");
        var msjFiltered = dataMensajes().stream()
                .filter(mensajes -> mensajes.getDescription().equalsIgnoreCase(description));
        return msjFiltered.collect(Collectors.toList());
    }

    @GetMapping("/mensajes/{id}")
    public Message getMensajeById(@PathVariable Long id) throws ApiRestException {
        log.info("GET obtener mensaje por el id");
        if (id == 0) {
            throw new ApiRestException("El identificador del mensaje debe ser mayor a 0");
        }
        var msjFiltered = dataMensajes().stream()
                .filter(mensajes -> Objects.equals(mensajes.getId(), id));
        return msjFiltered.findFirst().orElse(Message.of(0L, "No existe el mensaje"));
    }


    private List<Message> dataMensajes() {
        return List.of(
                Message.of(1L, "Mensaje-ABCD"),
                Message.of(2L, "Mensaje-ABCD"),
                Message.of(3L, "Mensaje-ABCD"),
                Message.of(4L, "Mensaje-ABCE"),
                Message.of(5L, "Mensaje-ABCF")
        );
    }
}
