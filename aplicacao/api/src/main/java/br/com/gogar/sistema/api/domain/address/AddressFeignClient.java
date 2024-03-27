package br.com.gogar.sistema.api.domain.address;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cep", url = "https://viacep.com.br/ws")
public interface AddressFeignClient {

    @GetMapping("{cep}/json")
    AddressResponse searchAddress(@PathVariable("cep") String cep);

}
