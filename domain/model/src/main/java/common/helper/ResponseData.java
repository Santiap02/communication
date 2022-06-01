package common.helper;

import co.com.bancolombia.model.inversionista.Inversionista;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class ResponseData {

    private List<Inversionista> data;
    private int status;
    private String message;
}
