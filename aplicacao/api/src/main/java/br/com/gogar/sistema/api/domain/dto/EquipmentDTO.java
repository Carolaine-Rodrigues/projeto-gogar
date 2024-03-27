package br.com.gogar.sistema.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDTO {

    private String name;
    private String image;
    private String description;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private Integer amount;
    private Integer totalAmount;


}
