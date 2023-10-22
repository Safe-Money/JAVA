package sptech.safemoney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.safemoney.dominio.Categoria;
import sptech.safemoney.dominio.Conta;
import sptech.safemoney.dominio.Usuario;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
    boolean existsById(Integer id);

}