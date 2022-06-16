package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.dto.NewReservationDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public NewReservationDTO newReservationMessage(@Payload NewReservationDTO message) {
        return message;
    }
}
