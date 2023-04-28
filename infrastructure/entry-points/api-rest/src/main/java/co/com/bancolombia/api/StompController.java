package co.com.bancolombia.api;

import co.com.bancolombia.model.inversionista.gateways.InversionistaRepository;
import co.com.bancolombia.model.messages.Hello;
import co.com.bancolombia.model.messages.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class StompController {

    private final InversionistaRepository repository;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Response greeting(Hello message) throws IOException {
        String rMessage;
        try {
            rMessage=  repository.findById(Long.parseLong(message.getId())).toString();
        } catch (NumberFormatException e) {
            rMessage= "Ese no es un numero";
        }
        return new Response(rMessage);
    }

}
