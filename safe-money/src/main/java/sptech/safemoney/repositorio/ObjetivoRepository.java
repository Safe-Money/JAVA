package sptech.safemoney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.safemoney.dominio.Objetivo;
import sptech.safemoney.dominio.UsuarioEntity;

import java.util.List;
import java.util.Optional;

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
}