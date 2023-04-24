package co.com.bancolombia.consumer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ObjectResponse {

    private Long id;

    @Size(
            min = 4,
            max = 200,
            message = "El campo nombre del Inversionista debe tener minimo 4 caracteres y maximo 200."
    )
    @NotBlank(
            message = "El campo nombre del Inversionista no puede estar vacio."
    )


    @Valid
    private String nombre;

}
