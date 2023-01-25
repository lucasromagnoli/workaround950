package br.com.lucasromagnoli.workaround.websocket;

import br.com.lucasromagnoli.workaround.tictoe.UglySession;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

//@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/hello")
    @SendToUser("/topic/greetings")
//    @SendToUser("/topic/greetings")
    public Greeting greeting(HelloMessage message, Principal principal) {
//        simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/topic/greetings", new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!"));
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/get_id")
    @SendToUser("/info/player")
    public String getId(String clientInfo, StompPrincipal principal) {
//        simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/info/player", principal.getName());
        UglySession.setPlayer(principal.getPlayer());
        return principal.getName();
    }
}
