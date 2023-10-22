package sptech.safemoney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.safemoney.dominio.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
    boolean existsById(Integer id);

}