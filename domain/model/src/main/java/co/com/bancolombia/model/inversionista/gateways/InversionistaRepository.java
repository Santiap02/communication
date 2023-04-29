package co.com.bancolombia.model.inversionista.gateways;

import co.com.bancolombia.model.inversionista.Inversionista;

import java.io.IOException;
import java.util.List;

public interface InversionistaRepository {

    List<Inversionista> findAll() throws IOException;

    Inversionista findById(Long id) throws IOException;

}
