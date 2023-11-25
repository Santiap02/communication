package co.com.bancolombia.api;

import co.com.bancolombia.authorization.annotations.SecuredTest;
import co.com.bancolombia.model.inversionista.Inversionista;
import co.com.bancolombia.usecase.inversionista.InversionistaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/consumo", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiRest {

    private final InversionistaUseCase inversionistaUseCase;

    @SecuredTest(role = {"ROLE_ADMIN"})
    @GetMapping(path = "/test")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")

    public List<Inversionista> findAll(@RequestHeader("Authorization") String token) throws Exception {
        return inversionistaUseCase.findAll();
    }

    @SecuredTest(role = "ROLE_INVITED")
    @GetMapping(path = "/test/{id}")
    @PreAuthorize("hasAuthority('ROLE_INVITED2')")
    public Inversionista findById(@RequestHeader("Authorization") String token, @PathVariable Long id) throws IOException {
        return inversionistaUseCase.findById(id);
    }

}
