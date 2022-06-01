package co.com.bancolombia.feignhelper;

import common.helper.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "inversionista", url = "http://localhost:8080/api/v1/")
public interface FeignClientInversionista {


    @GetMapping(value = "inversionistas/")
    ResponseData findAll();

}
