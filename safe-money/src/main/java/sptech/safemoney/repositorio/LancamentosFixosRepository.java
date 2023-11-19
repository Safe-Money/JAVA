package sptech.safemoney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.safemoney.dominio.LancamentosFixos;

import java.time.LocalDate;
import java.util.List;

public interface LancamentosFixosRepository extends JpaRepository<LancamentosFixos, Integer> {
    @Query("select sum(valor) from LancamentosFixos l where l.fkConta.fkUsuario.id = ?1 and " +
            "fkSituacao.id = 2")
    double despesaPrevistaFixa(int idUsuario);


    @Query("""
            select sum(valor) from Transacao t where t.conta.fkUsuario.id = ?1 and 
            MONTH(t.data) = MONTH(?2) and YEAR(t.data) = YEAR(?2) and t.tipo.id in (3)
            """)
    double receitaPrevista(int idUsuario);

    @Query("""
    select l from LancamentosFixos where l.fkConta.id = ?1        
            """)
    List<LancamentosFixos> getLancamentosFixosPorConta(int idConta);
}
