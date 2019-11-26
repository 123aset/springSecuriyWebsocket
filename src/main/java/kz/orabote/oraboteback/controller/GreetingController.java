package kz.orabote.oraboteback.controller;

import kz.orabote.oraboteback.model.Greeting;
import kz.orabote.oraboteback.model.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import javax.websocket.server.PathParam;

@Controller
public class GreetingController {

    @Autowired
    public SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/hello/{username}")
//    @SendTo("/topic/greetings/")
    public void greeting(@Payload HelloMessage message, @DestinationVariable("username") String username) throws Exception {
        System.out.println(message.getName());
        System.out.println(username);
        this.simpMessagingTemplate.convertAndSend("/topic/greetings/" + username, message);
//        return new Greeting("Hello, "+ username + "! Message: " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @RequestMapping("test")
    public String test() {
        HelloMessage helloMessage = new HelloMessage();
        helloMessage.setName("HElllfew");
        simpMessagingTemplate.convertAndSend("/topic/greetings/123aset", helloMessage);
        return "";
    }
}
