package sptech.safemoney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.safemoney.dominio.PagamentoEntity;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Integer> {
}
