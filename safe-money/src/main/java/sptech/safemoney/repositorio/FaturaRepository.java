package sptech.safemoney.repositorio;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sptech.safemoney.dominio.Fatura;
import sptech.safemoney.dto.req.CartaoCreditoDTO;

import java.time.LocalDate;
import java.util.List;

public interface FaturaRepository extends JpaRepository<Fatura, Integer> {
    @Query("""
    select sum(valor) from Fatura f where f.fkCartao.conta.fkUsuario.id = ?1           
    """)
    Double faturaTotal(int idUsuario);

    @Query("""
    select sum(valor) from Fatura f where f.fkCartao.conta.id = ?1           
    """)
    Double faturaTotalConta(int idUsuario);

    @Modifying
    @Transactional
    @Query("""
    update Fatura f set f.valor = f.valor + ?1 where f.fkCartao.id = ?2 
            """)
    int atualizarFatura(double valor, int fkCartao);

    @Query("""
    select valor from Fatura f where f.fkCartao.id = ?1           
    """)
    double buscarLimiteAtual(int idCartao);

    @Query("""
    select fkCartao.id from Fatura f where f.id = ?1
            """)
    int buscarFkCartao(int idFatura);

    /*

    @Query("""
    select new sptech.safemoney.dto.req.CartaoFaturaDTO(c.nome, c.bandeira, c.limite, c.dataFechamento, c.dataVencimento, f.dataReferencia, f.valor)  from Fatura f join f.fkCartao c where f.fkSituacao.id = 1 and c.conta.fkUsuario.id = ?1        
            """)
    List<CartaoCreditoDTO> getFaturasNaoPagas(int idUsuario);

     */

    @Query("""
    select f.valor from Fatura f where f.fkCartao.id = ?1 and MONTH(f.dataReferencia) = MONTH(?2)
            """)
    double buscarFaturaValor(int id,LocalDate dataAtual);

    @Query("""
    select f from Fatura f where f.fkCartao.id = ?1
            """)
    List<Fatura> buscarFaturaPorCartao(int fkCartao);

    List<Fatura> findByFkCartaoId(int fkCartao);

    @Query("""
    select f from Fatura f where f.dataReferencia >= ?1 and f.fkCartao.id = ?2
            """)
    List<Fatura> getFaturasAbertas(LocalDate data, int idCartao);
}
