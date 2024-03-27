package br.com.gogar.sistema.api.domain.entity;

import br.com.gogar.sistema.api.domain.dto.FileDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "tb_file")
@Data
public class ProposalFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameService;
    private Integer amount;
    private BigDecimal priceProposal;
    private String description;

    public ProposalFile(FileDTO data){
        this.nameService = data.getNameService();
        this.amount = data.getAmount();
        this.description = data.getDescription();
        this.priceProposal = data.getPriceProposal();
    }


}
