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
}