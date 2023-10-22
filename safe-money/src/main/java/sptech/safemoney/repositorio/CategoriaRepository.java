package sptech.safemoney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.safemoney.dominio.Categoria;
import sptech.safemoney.dominio.Conta;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}