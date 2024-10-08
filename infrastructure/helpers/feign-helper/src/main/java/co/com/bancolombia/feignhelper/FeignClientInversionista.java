package co.com.bancolombia.feignhelper;

import co.com.bancolombia.model.inversionista.Inversionista;
import common.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "inversionista", url = "http://localhost:8080/api/v1/")
public interface FeignClientInversionista {


    @GetMapping(value = "inversionista")
    ResponseData<List<Inversionista>> findAll();

    @GetMapping(value = "inversionista/{id}")
    ResponseData<Inversionista> findById(@PathVariable("id") Long id);

}
