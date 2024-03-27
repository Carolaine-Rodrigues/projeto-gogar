package br.com.gogar.sistema.api.domain.repository;

import br.com.gogar.sistema.api.domain.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

}
