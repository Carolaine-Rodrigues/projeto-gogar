package br.com.gogar.sistema.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {

    private String nameService;
    private Integer amount;
    private BigDecimal priceProposal;
    private String description;
}
