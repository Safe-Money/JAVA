package sptech.safemoney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sptech.safemoney.dominio.Planejamento;
import sptech.safemoney.dominio.Transacao;

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

/*
    @Query("SELECT SUM(p.valor) " +
            "FROM Planejamento p where ")
    List<Object[]> valorPlanejadoNoMes(@Param("id") int id, @Param("mes") int mes);
 */
}