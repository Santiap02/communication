package co.com.bancolombia.api;

import co.com.bancolombia.model.inversionista.Inversionista;
import co.com.bancolombia.usecase.inversionista.InversionistaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/consumo", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiRest {

    private final InversionistaUseCase inversionistaUseCase;

    //@SecuredTest(role = {"ROLE_ADMIN"})
    @GetMapping(path = "/test")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Inversionista> findAll(@RequestHeader("Authorization") String token) throws Exception {
        return inversionistaUseCase.findAll();
    }

    //@SecuredTest(role = "ROLE_INVITED")
    @GetMapping(path = "/test/{id}")
    //@PreAuthorize("hasAuthority('ROLE_INVITED2')")
    public Inversionista findById(@RequestHeader("Authorization") String token, @PathVariable("id") Long id) throws IOException {
        return inversionistaUseCase.findById(id);
    }

}
