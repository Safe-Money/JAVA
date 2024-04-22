package sptech.safemoney.repositorio;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sptech.safemoney.dominio.UsuarioEntity;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    boolean existsByEmail(String email);

    Optional<UsuarioEntity> findByEmail(String email);

    boolean existsByEmailAndSenha(String email, String senha);

    @Modifying
    @Transactional
    @Query("""
    update UsuarioEntity u set u.plano = 1 where u.id = ?1
            """)
    int alterarPlano(int idUsuario);
}