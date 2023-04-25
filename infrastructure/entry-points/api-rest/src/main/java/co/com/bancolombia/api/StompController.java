package co.com.bancolombia.api;

import co.com.bancolombia.model.messages.Hello;
import co.com.bancolombia.model.messages.Response;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class StompController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Response greeting(Hello message) {
        return new Response("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
