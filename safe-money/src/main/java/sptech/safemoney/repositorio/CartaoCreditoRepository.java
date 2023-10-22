package sptech.safemoney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.safemoney.dominio.CartaoCredito;
import sptech.safemoney.dominio.Usuario;

public interface CartaoCreditoRepository extends JpaRepository<CartaoCredito, Integer> {
    boolean existsById(Integer id);
}