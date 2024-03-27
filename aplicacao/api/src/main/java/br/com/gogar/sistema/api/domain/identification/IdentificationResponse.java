package br.com.gogar.sistema.api.domain.identification;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class IdentificationResponse {

    private String cnpj;
    @JsonProperty("cnpj_raiz")
    private String rootCnpj;
    @JsonProperty("razao_social")
    private String corporateReason;
}
