package co.com.bancolombia.consumer;

import co.com.bancolombia.model.inversionista.Inversionista;
import co.com.bancolombia.model.inversionista.gateways.InversionistaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.ResponseData;
import common.exceptions.ServiceException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Primary
@Repository
@RequiredArgsConstructor
public class RestConsumer implements InversionistaRepository
{

    @Value("${adapter.restconsumer.url1}")
    private String url1;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    @Override
    @CircuitBreaker(name = "findAllInversionista", fallbackMethod = "fallBack")
    public List<Inversionista> findAll() throws IOException {
        Request request = new Request.Builder()
            .url(url1)
            .get()
            .addHeader("Content-Type","application/json")
            .build();
        var response = (List<Object>) mapper
                .readValue(client.newCall(request).execute().body().string(), ResponseData.class).getData();
        return response.stream()
                .map(o -> mapper.convertValue(o, Inversionista.class))
                .toList();
    }

    @Override
    @Bulkhead(name = "backendA", type = Bulkhead.Type.SEMAPHORE, fallbackMethod = "fallBackId")
    public Inversionista findById(Long id) throws IOException {
        try {
            Request request = new Request.Builder()
                    .url(url1 + "/" + id)
                    .get()
                    .addHeader("Content-Type","application/json")
                    .build();
            Thread.sleep(100000);
            var response = mapper
                    .readValue(client.newCall(request).execute().body().string(), ResponseData.class);
            return mapper.convertValue(response.getData(), Inversionista.class);
        }catch (InterruptedException e){
            return null;
        }
    }


    public List<Inversionista> fallBack(Throwable e) throws ServiceException {
        throw new ServiceException("El servicio no se encuentra disponible, intente en un momento");
    }

    public Inversionista fallBackId(Long id, Throwable e) throws ServiceException {
        throw new ServiceException("El servicio no se encuentra disponible, intente en un momento");
    }

}
