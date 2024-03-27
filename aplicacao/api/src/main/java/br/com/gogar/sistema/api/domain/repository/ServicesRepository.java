package br.com.gogar.sistema.api.domain.repository;

import br.com.gogar.sistema.api.domain.entity.ServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<ServicesEntity, Long> {

}
