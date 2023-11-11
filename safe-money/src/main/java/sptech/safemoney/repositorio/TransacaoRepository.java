package sptech.safemoney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sptech.safemoney.dominio.Categoria;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.utils.ListaObj;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
    @Query("SELECT t, c.nome FROM Transacao t JOIN t.categoria c")
    List<Object[]> findAllTransacoesWithCategoriaName();
    boolean existsById(Integer id);

    @Query("SELECT t FROM Transacao t JOIN t.conta c WHERE c.fkUsuario.id = :id")
    List<Transacao> findAllTransacoesByUserId(@Param("id") int id);
}