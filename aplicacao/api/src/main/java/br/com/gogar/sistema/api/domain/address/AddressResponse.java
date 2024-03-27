package br.com.gogar.sistema.api.domain.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class AddressResponse {

    private String cep;
    @JsonProperty("logradouro")
    private String publicPlace;
    @JsonProperty("bairro")
    private String neighborhood;
    @JsonProperty("localidade")
    private String locality;
    private String uf;


}
