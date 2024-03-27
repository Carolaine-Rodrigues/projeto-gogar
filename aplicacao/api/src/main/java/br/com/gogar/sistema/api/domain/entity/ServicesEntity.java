package br.com.gogar.sistema.api.domain.entity;

import br.com.gogar.sistema.api.domain.dto.ServicesDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_training")
public class ServicesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String typeTraining;
    private BigDecimal monthlyResalePrice;
    private BigDecimal distributorMonthlyPrice;
    private BigDecimal userPrice;
    private LocalDate productHours;
    private String description;

    public ServicesEntity(ServicesDTO data){
      this.name = data.getName();
      this.description = data.getDescription();
      this.typeTraining = data.getTypeTraining();
      this.distributorMonthlyPrice = data.getDistributorMonthlyPrice();
      this.monthlyResalePrice = data.getMonthlyResalePrice();
      this.userPrice = data.getUserPrice();
      this.productHours = data.getProductHours();
    }
}
