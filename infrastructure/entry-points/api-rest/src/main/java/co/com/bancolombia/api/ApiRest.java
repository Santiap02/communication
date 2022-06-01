package co.com.bancolombia.api;
import co.com.bancolombia.model.inversionista.Inversionista;
import co.com.bancolombia.usecase.inversionista.InversionistaUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/consumo", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {

    InversionistaUseCase inversionistaUseCase;


    @GetMapping(path = "/test")
    public List<Inversionista> commandName() throws IOException {
         return inversionistaUseCase.getAllInversionistas();
    }
}
