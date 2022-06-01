package co.com.bancolombia.feignconsumer;

import co.com.bancolombia.feignhelper.FeignClientInversionista;
import co.com.bancolombia.model.inversionista.Inversionista;
import co.com.bancolombia.model.inversionista.gateways.InversionistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Order(1)
@Service
@RequiredArgsConstructor
public class FeignConsumer implements InversionistaRepository {
    private final FeignClientInversionista client;


    @Override
    public List<Inversionista> getAllInversionistas(){
        return client.findAll().getData();
    }

}
