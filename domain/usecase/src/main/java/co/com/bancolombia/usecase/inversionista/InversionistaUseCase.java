package co.com.bancolombia.usecase.inversionista;

import co.com.bancolombia.model.inversionista.Inversionista;
import co.com.bancolombia.model.inversionista.gateways.InversionistaRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
public class InversionistaUseCase {

    private final InversionistaRepository inversionistaRepository;

    public List<Inversionista> getAllInversionistas() throws IOException {
        return inversionistaRepository.getAllInversionistas();
    }
    
}
