package sptech.safemoney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.safemoney.dominio.Fatura;
import sptech.safemoney.dominio.Situacao;

public interface SituacaoRepository extends JpaRepository<Situacao, Integer> {

}
