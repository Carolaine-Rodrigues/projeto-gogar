package br.com.gogar.sistema.api.domain.entity;

import br.com.gogar.sistema.api.domain.dto.EquipmentDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="tb_equipment")
@ToString
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private Integer amount;
    private Integer totalAmount;

    public Equipment(EquipmentDTO data){
        this.name = data.getName();
        this.description = data.getDescription();
        this.price = data.getPrice();
        this.totalPrice = data.getTotalPrice();
        this.amount = data.getAmount();
        this.totalAmount = data.getTotalAmount();
    }

}
