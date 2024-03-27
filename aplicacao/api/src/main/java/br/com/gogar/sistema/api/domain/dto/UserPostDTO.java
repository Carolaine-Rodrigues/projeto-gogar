package br.com.gogar.sistema.api.domain.dto;

import br.com.gogar.sistema.api.domain.address.AddressResponse;
import br.com.gogar.sistema.api.domain.identification.IdentificationResponse;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPostDTO {

    @Embedded
    private IdentificationResponse identification;
    @Embedded
    private AddressResponse address;
    private String phone;
    private String url;
    private String email;
    private String password;

}
