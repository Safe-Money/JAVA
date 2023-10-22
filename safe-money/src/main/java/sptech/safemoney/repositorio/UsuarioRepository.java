package sptech.safemoney.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.safemoney.dominio.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    boolean existsByEmail(String email);

    Optional<UsuarioEntity> findByEmail(String email);

    boolean existsByEmailAndSenha(String email, String senha);
}