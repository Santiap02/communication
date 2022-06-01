package co.com.bancolombia.model.inversionista;


import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Inversionista {

    private Long id;


    private String nombre;
}
