package co.com.bancolombia.consumer;

    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import javax.validation.Valid;
    import javax.validation.constraints.NotBlank;
    import javax.validation.constraints.Size;

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
