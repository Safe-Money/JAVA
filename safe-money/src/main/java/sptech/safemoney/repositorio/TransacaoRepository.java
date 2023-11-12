package sptech.safemoney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sptech.safemoney.dominio.Categoria;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.utils.ListaObj;

import java.time.LocalDate;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
    @Query("SELECT t, c.nome FROM Transacao t JOIN t.categoria c")
    List<Object[]> findAllTransacoesWithCategoriaName();
    boolean existsById(Integer id);

    @Query("SELECT t FROM Transacao t JOIN t.conta c WHERE c.fkUsuario.id = :id")
    List<Transacao> findAllTransacoesByUserId(@Param("id") int id);

    @Query("select t from Transacao t left join t.fatura f left join f.fkCartao cc left join cc.conta c where c.fkUsuario.id = ?1")
    List<Transacao> getUltimosGastosCredito(int idUsuario);

    @Query("select t from Transacao t left join t.conta c where c.fkUsuario.id = ?1")
    List<Transacao> getUltimosGastosDebito(int idUsuario);

    @Query("select sum(valor) from Transacao t where t.conta.fkUsuario.id = ?1 and " +
            "MONTH(t.data) = MONTH(CURRENT_DATE) and YEAR(t.data) = YEAR(CURRENT_DATE) and t.tipo.id in (1, 2, 3)")
    double receitaPrevista(int idUsuario);
}