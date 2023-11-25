package co.com.bancolombia.consumer;

import co.com.bancolombia.model.inversionista.Inversionista;
import co.com.bancolombia.model.inversionista.gateways.InversionistaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.helper.ResponseData;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

//@Primary
@Component
@RequiredArgsConstructor
public class RestConsumer implements InversionistaRepository
{

    @Value("${adapter.restconsumer.url1}")
    private String url1;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

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
    public Inversionista findById(Long id) throws IOException {
        Request request = new Request.Builder()
                .url(url1 + "/" + id)
                .get()
                .addHeader("Content-Type","application/json")
                .build();
        var response = mapper
                .readValue(client.newCall(request).execute().body().string(), ResponseData.class);
        return mapper.convertValue(response.getData(), Inversionista.class);
    }



}
