package co.com.bancolombia.api;

import co.com.bancolombia.model.messages.Hello;
import co.com.bancolombia.model.messages.Response;
import co.com.bancolombia.usecase.inversionista.InversionistaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class StompController {

    private final InversionistaUseCase repository;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Response findById(Hello message) throws IOException {
        String responseMessage;
        try {
            responseMessage=  repository.findById(Long.parseLong(message.getId())).toString();
        } catch (NumberFormatException e) {
            responseMessage= "Ese no es un numero";
        }
        return new Response(responseMessage);
    }

}
