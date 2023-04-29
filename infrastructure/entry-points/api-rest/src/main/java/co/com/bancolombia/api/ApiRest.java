package co.com.bancolombia.api;

import co.com.bancolombia.model.inversionista.Inversionista;
import co.com.bancolombia.usecase.inversionista.InversionistaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/consumo", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiRest {

    private final InversionistaUseCase inversionistaUseCase;

    @GetMapping(path = "/test")
    public List<Inversionista> findAll() throws IOException {
         return inversionistaUseCase.findAll();
    }

    @GetMapping(path = "/test/{id}")
    public Inversionista findById(@PathVariable Long id) throws IOException {
        return inversionistaUseCase.findById(id);
    }

}
