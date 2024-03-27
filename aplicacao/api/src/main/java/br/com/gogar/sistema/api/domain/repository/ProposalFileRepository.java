package br.com.gogar.sistema.api.domain.repository;

import br.com.gogar.sistema.api.domain.entity.ProposalFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalFileRepository extends JpaRepository<ProposalFile, Long> {

}
