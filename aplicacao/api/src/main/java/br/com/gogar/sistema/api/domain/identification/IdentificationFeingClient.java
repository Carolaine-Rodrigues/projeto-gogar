package br.com.gogar.sistema.api.domain.identification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cnpj", url = "https://publica.cnpj.ws/")
public interface IdentificationFeingClient {

    @GetMapping("/cnpj/{cnpj}")
    IdentificationResponse searchIdentication(@PathVariable("cnpj") String cnpj);


}
