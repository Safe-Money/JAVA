package sptech.safemoney.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sptech.safemoney.dominio.Categoria;
import sptech.safemoney.dominio.Planejamento;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.dto.req.CategoriaValorPlanejadoDTO;
import sptech.safemoney.dto.res.GastoCategoriaDTO;

import java.time.LocalDate;
import java.util.List;

public interface PlanejamentoRepository extends JpaRepository<Planejamento, Integer> {
    @Query("SELECT p FROM Planejamento p JOIN p.usuario u WHERE u.id = :id")
    List<Planejamento> findAllPlanejamentosByUserId(@Param("id") int id);
    @Query("SELECT p.categoria, p.valorPlanejado, COUNT(p), SUM(t.valor) " +
            "FROM Planejamento p " +
            "JOIN p.usuario u " +
            "LEFT JOIN Transacao t ON t.categoria = p.categoria AND MONTH(t.data) = :mes " +
            "WHERE u.id = :id AND MONTH(p.data) = :mes " +
            "GROUP BY p.categoria, p.valorPlanejado")
    List<Object[]> countPlanejamentosByCategoryAndUserIdAndMonth(@Param("id") int id, @Param("mes") int mes);
    @Query("SELECT SUM(p.valorPlanejado) " +
            "FROM Planejamento p " +
            "JOIN p.usuario u " +
            "WHERE u.id = :id AND MONTH(p.data) = :mes ")
    Double valorPlanejadoDoMes(@Param("id") int id, @Param("mes") int mes);

    @Query("SELECT SUM(t.valor) " +
            "FROM Transacao t " +
            "JOIN t.conta c " +
            "JOIN c.fkUsuario u " +
            "WHERE u.id = :id " +
            "AND MONTH(t.data) = :mes")
    Double valorGastoDoMes(@Param("id") int id, @Param("mes") int mes);


    @Query("SELECT t.categoria " +
            "FROM Transacao t " +
            "JOIN t.conta c " +
            "JOIN c.fkUsuario u " +
            "WHERE u.id = :id " +
            "AND MONTH(t.data) = :mes " +
            "GROUP BY t.categoria " +
            "HAVING SUM(t.valor) = (SELECT MAX(totalGasto) FROM (SELECT SUM(valor) as totalGasto FROM Transacao tt JOIN tt.conta cc JOIN cc.fkUsuario uu WHERE uu.id = :id AND MONTH(tt.data) = :mes GROUP BY tt.categoria) as subquery)")
    Categoria findTopCategoriaByUsuarioIdAndMesOrderByTotalGastoDesc(@Param("id") int id, @Param("mes") int mes);



    @Query("""
    select new sptech.safemoney.dto.req.CategoriaValorPlanejadoDTO(c.id, p.id, p.valorPlanejado) from Planejamento p join p.categoria c where p.usuario.id = ?1 
            """)
    List<CategoriaValorPlanejadoDTO> getCategoriasPlanejadas(int id);


    @Query("""
     select sum(t.valor) from Transacao t join t.categoria c
     where t.categoria.id = ?1 and MONTH(t.data) = MONTH(?2)      
            """)
    Double getGastoDTO(int id, LocalDate dataAtual);


/*
    @Query("SELECT SUM(p.valor) " +
    "FROM Planejamento p where ")
    List<Object[]> valorPlanejadoNoMes(@Param("id") int id, @Param("mes") int mes);
 */
}