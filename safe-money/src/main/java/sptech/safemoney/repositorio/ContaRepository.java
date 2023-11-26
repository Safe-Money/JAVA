package sptech.safemoney.repositorio;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sptech.safemoney.dominio.ContaEntity;

import java.util.List;
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
           
    @Modifying
    @Transactional
    @Query("""
    update ContaEntity c set c.saldo = c.saldo + ?1 where c.id = ?2            
    """)
    int acrescentarSaldo(Double receita, int idConta);


    @Query("""
    select sum(saldo) from ContaEntity c where c.fkUsuario.id = ?1           
    """)
    Double saldoTotal(int idConta);

    @Query("""
    select sum(valor) from Transacao t where t.conta.fkUsuario.id = ?1 and DAY(t.data) <= DAY(CURRENT_DATE) and MONTH(t.data) = MONTH(CURRENT_DATE) and YEAR(t.data) = YEAR(CURRENT_DATE) and t.tipo.id in (1, 2, 3)          
    """)
    Double despesaTotal(int idConta);


    @Query("""
    select sum(saldo) from ContaEntity c where c.id = ?1           
    """)
    double saldoTotalConta(int idConta);

    @Query("""
    select sum(valor) from Transacao t where t.conta.id = ?1 and DAY(t.data) <= DAY(CURRENT_DATE) and MONTH(t.data) = MONTH(CURRENT_DATE) and YEAR(t.data) = YEAR(CURRENT_DATE) and t.tipo.id in (1, 2, 3)          
    """)
    double despesaTotalConta(int idConta);


    // QUERY MUITO BOA, MAS SEM UTILIZAÇÃO NO MOMENTO
    @Query("""
    select sum(valor) from Transacao t where t.conta.fkUsuario.id = ?1 and DAY(t.data) <= DAY(CURRENT_DATE) and MONTH(t.data) = MONTH(CURRENT_DATE) and YEAR(t.data) = YEAR(CURRENT_DATE) and t.tipo.id in (1, 2, 3)          
    """)
    double gastoCartoes(int idConta);


    @Query("""
    select c from ContaEntity c where c.fkUsuario.id = ?1
            """)
    List<ContaEntity> listarContas(int idUsuario);
}