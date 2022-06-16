package com.fontys.s3itproject.controller;

import com.fontys.s3itproject.model.Greeting;
import com.fontys.s3itproject.model.HelloMessage;
import com.fontys.s3itproject.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class WebsocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public Message message(HelloMessage name) throws Exception {
        Thread.sleep(1000);
        return new Message("A new reservation has been made");
    }
}
