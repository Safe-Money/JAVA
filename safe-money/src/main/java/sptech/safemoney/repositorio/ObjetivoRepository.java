package sptech.safemoney.repositorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sptech.safemoney.dominio.Objetivo;
public interface ObjetivoRepository extends JpaRepository<Objetivo, Integer> {
    @Query("""
    SELECT SUM(o.valorInvestido) 
    FROM Objetivo o
    JOIN o.fkUsuario u
    WHERE u.id = ?1 
    """)
    Double saldoTotal(int id);
    @Query("""
    SELECT o
    FROM Objetivo o
    JOIN o.fkUsuario u
    WHERE u.id = ?1
    ORDER BY o.dataTermino ASC
    LIMIT 1
""")
    Objetivo objetivoProximos(int id);

    @Modifying
    @Query("""
    UPDATE Objetivo o
    SET o.valorInvestido = :novoValorInvestido
    WHERE o.id = :idDoObjetivo
    AND o.fkUsuario.id = :idDoUsuario
    """)
    Double atualizarValorInvestido(@Param("idDoObjetivo") int idDoObjetivo, @Param("novoValorInvestido") double novoValorInvestido, @Param("idDoUsuario") int idDoUsuario);

}