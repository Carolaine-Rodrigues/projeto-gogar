package br.com.gogar.sistema.api.domain.identification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdenticationRequest {

    private String cnpj;
}
