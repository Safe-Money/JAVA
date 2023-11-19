package sptech.safemoney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.safemoney.dominio.CartaoCredito;
import sptech.safemoney.dominio.ContaEntity;

import java.util.List;

public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Integer> {
    boolean existsById(Integer id);

    @Query("""
    select c from CartaoCredito c where c.conta.fkUsuario.id = ?1
            """)
    List<CartaoCredito> listarCartaoCredito(int idUsuario);

    @Query("""
    select c from CartaoCredito c where c.conta.id = ?1
            """)
    List<CartaoCredito> listarCartaoCreditoConta(int idConta);

    @Query("""
    select c from CartaoCredito c where c.id = ?1
            """)
    CartaoCredito listarUmCartaoCredito(int idCartao);

    @Query("""
    select c from CartaoCredito c join Fatura f where f.id = ?1       
            """)
    CartaoCredito buscarCartaoCreditoPorFatura(int idFatura);
}