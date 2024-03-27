package br.com.gogar.sistema.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicesDTO {

    private String name;
    private BigDecimal monthlyResalePrice;
    private BigDecimal distributorMonthlyPrice;
    private BigDecimal userPrice;
    private LocalDate productHours;
    private String typeTraining;
    private String description;

    public ServicesDTO (int hours, int minutes){
        this.productHours = LocalDate.from(LocalTime.of(hours,minutes));
    }
}
