package sptech.safemoney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sptech.safemoney.dominio.Categoria;
import sptech.safemoney.dominio.LancamentosFixos;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.dto.res.GastoPorDiaDTO;
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

    @Query("select t from Transacao t left join t.fatura f left join f.fkCartao cc left join cc.conta c where c.id = ?1 and MONTH(t.data) = 2")
    List<Transacao> getUltimosGastosCreditoConta(int idConta, int mes);

    @Query("select t from Transacao t left join t.conta c where c.id = ?1 and MONTH(t.data) = ?2")
    List<Transacao> getUltimosGastosDebitoConta(int idConta, int mes);

    @Query("select sum(valor) from Transacao t where t.conta.fkUsuario.id = ?1 and " +
            "MONTH(t.data) = MONTH(?2) and YEAR(t.data) = YEAR(?2) and t.tipo.id in (3)")
    double despesaPrevistaCartao(int idUsuario, LocalDate data);

    @Query("""
    select t.data, sum(t.valor) from Transacao t where t.conta = ?1 group by t.data        
            """)
    List<GastoPorDiaDTO> getGastoPorDia(int idConta);


    @Query("""
                select ca.imagemAssociada, t.valor, t.data, ti.nome, c.nome from Transacao t
                JOIN t.tipo ti
                JOIN t.categoria ca
                JOIN t.conta c
                JOIN c.fkUsuario u
                where u.id =?1 order by t.valor desc
            """)
    List<Object> transacoesPorValor(int id);

    @Query("""
                select ca.imagemAssociada, t.valor, t.data, ti.nome, c.nome from Transacao t
                JOIN t.tipo ti
                JOIN t.categoria ca
                JOIN t.conta c
                JOIN c.fkUsuario u
                where u.id =?1 order by t.data desc
            """)
    List<Object> transacoesPorData(int id);


    @Query("""
                select ca.nome, sum(t.valor) from Transacao t
                JOIN t.tipo ti
                JOIN t.categoria ca
                JOIN t.conta c
                JOIN c.fkUsuario u
                where u.id =?1 and ti.nome = 'despesa' group by ca.nome
            """)
    List<Object> graficoGastosPorcategoria(int id);

    @Query("""
    SELECT ca.nome, SUM(t.valor) as total_valor 
    FROM Transacao t
    JOIN t.tipo ti
    JOIN t.categoria ca
    JOIN t.conta c
    JOIN c.fkUsuario u
    WHERE u.id = ?1 AND ti.nome = 'despesa' 
    GROUP BY ca.nome
    ORDER BY total_valor DESC
""")
    List<Object> top5CategoriasMaisGasto(int id);





}