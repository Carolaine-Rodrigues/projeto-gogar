package br.com.gogar.sistema.api.domain.entity;

import br.com.gogar.sistema.api.domain.address.AddressResponse;
import br.com.gogar.sistema.api.domain.dto.UserPostDTO;
import br.com.gogar.sistema.api.domain.identification.IdentificationResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Embedded
    @Column(insertable=false, updatable=false)
    private IdentificationResponse cnpj;
    @Embedded
    private AddressResponse address;
    private String phone;
    private String email;
    private String password;



    public User(UserPostDTO data){
        this.cnpj = data.getIdentification();
        this.address = data.getAddress();
        this.phone = data.getPhone();
        this.email = data.getEmail();
        this.password = data.getPassword();

    }



    public IdentificationResponse getCnpj() {
        return cnpj;
    }

    public void setCnpj(IdentificationResponse cnpj) {
        this.cnpj = cnpj;
    }

    public AddressResponse getAddress() {
        return address;
    }

    public void setAddress(AddressResponse address) {
        this.address = address;
    }
}
