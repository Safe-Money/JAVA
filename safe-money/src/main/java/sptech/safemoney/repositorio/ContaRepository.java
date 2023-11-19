package sptech.safemoney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.safemoney.dominio.ContaEntity;

public interface ContaRepository extends JpaRepository<ContaEntity, Integer> {
    boolean existsById(Integer id);

}