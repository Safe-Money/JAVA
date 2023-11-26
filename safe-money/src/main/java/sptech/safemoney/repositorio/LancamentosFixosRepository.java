package sptech.safemoney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.safemoney.dominio.LancamentosFixos;

import java.time.LocalDate;
import java.util.List;

public interface LancamentosFixosRepository extends JpaRepository<LancamentosFixos, Integer> {
    @Query("select sum(valor) from LancamentosFixos l where l.fkConta.fkUsuario.id = ?1 and " +
            "l.fkTipoTransacao.id = 2")
    Double despesaPrevistaFixa(int idUsuario);


    @Query("""
            select sum(valor) from LancamentosFixos l where l.fkConta.fkUsuario.id = ?1 and l.fkTipoTransacao.id = 5
            """)
    Double receitaPrevista(int idUsuario);

    @Query("""
    select l from LancamentosFixos l where l.fkConta.id = ?1        
            """)
    List<LancamentosFixos> getLancamentosFixosPorConta(int idConta);
}
