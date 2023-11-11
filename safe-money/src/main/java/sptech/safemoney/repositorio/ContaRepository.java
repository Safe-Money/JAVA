package sptech.safemoney.repositorio;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sptech.safemoney.dominio.ContaEntity;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<ContaEntity, Integer> {
    boolean existsById(Integer id);

    @Modifying
    @Transactional
    @Query("""
    update ContaEntity c set c.saldo = c.saldo - ?1 where c.id = ?2            
    """)
    int descontarSaldo(Double despesa, int idConta);

    @Query("""
    select saldo from ContaEntity c where c.id = ?1           
    """)
    double buscarSaldoAtual(int idConta);

}