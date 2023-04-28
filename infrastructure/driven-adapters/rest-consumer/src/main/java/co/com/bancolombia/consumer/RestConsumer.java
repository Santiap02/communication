package co.com.bancolombia.consumer;

import co.com.bancolombia.model.inversionista.Inversionista;
import co.com.bancolombia.model.inversionista.gateways.InversionistaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.helper.ResponseData;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestConsumer implements InversionistaRepository
{

    @Value("${adapter.restconsumer.url1}")
    private String url1;
    @Value("${adapter.restconsumer.url2}")
    private String url2;
    private final OkHttpClient client;
    private final ObjectMapper mapper;

    public List<Inversionista> getAllInversionistas() throws IOException {
        Request request = new Request.Builder()
            .url(url1)
            .get()
            .addHeader("Content-Type","application/json")
            .build();
        var a = (List<Object>) mapper.readValue(client.newCall(request).execute().body().string(), ResponseData.class).getData();
        return a.stream()
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
        var response = mapper.readValue(client.newCall(request).execute().body().string(), ResponseData.class);
        return mapper.convertValue(response.getData(), Inversionista.class);
    }

//    public ObjectResponse testPost() throws IOException {
//        String json = mapper.writeValueAsString(ObjectRequest.builder()
//            .val1("exampleval1")
//            .val2("exampleval1")
//            .build()
//        );
//        RequestBody requestBody = RequestBody
//            .create(MediaType.parse("application/json; charset=utf-8"), json);
//        Request request = new Request.Builder()
//            .url(url2)
//            .post(requestBody)
//            .addHeader("Content-Type","application/json")
//            .build();
//        var response = client.newCall(request).execute().body().string();
//        return mapper.readValue(response, ObjectResponse.class);
//    }

}
