package br.com.gogar.sistema.api;

import br.com.gogar.sistema.api.domain.address.AddressFeignClient;
import br.com.gogar.sistema.api.domain.identification.IdentificationFeingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableFeignClients
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Autowired
	AddressFeignClient addressFeignClient;
	@Autowired
	IdentificationFeingClient identificationFeingClient;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.addressFeignClient.searchAddress("08120470"));
		System.out.println(this.identificationFeingClient.searchIdentication("47960950089785"));
	}
}
