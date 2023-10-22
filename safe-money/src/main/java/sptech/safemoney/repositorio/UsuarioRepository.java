package sptech.safemoney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sptech.safemoney.dominio.Conta;
import sptech.safemoney.dominio.Usuario;
=======
import sptech.safemoney.dominio.UsuarioEntity;
>>>>>>> 70decc2e2665862044f93f9e42fdc7265de75c06

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    boolean existsByEmail(String email);

    Optional<UsuarioEntity> findByEmail(String email);

    boolean existsByEmailAndSenha(String email, String senha);
}