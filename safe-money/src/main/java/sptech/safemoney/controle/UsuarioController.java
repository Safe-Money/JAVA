package sptech.safemoney.controle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sptech.safemoney.dominio.UsuarioEntity;
import sptech.safemoney.dto.UsuarioCadastroDTO;
import sptech.safemoney.dto.res.UsuarioUpdateDTO;
import sptech.safemoney.repositorio.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Tag(name = "Usuario Controller", description = "CRUD de usuários")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @Operation(summary = "Busca e Lista todos os usuários salvos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados"),
            @ApiResponse(responseCode = "204", description = "Lista de usuário vazia")
    })
    @GetMapping("/")
    public ResponseEntity<List<UsuarioEntity>> getAllUsers() {
        List<UsuarioEntity> listaUsuarios = repository.findAll();

        return listaUsuarios.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaUsuarios);
    }

    @Operation(summary = "Busca e lista um usuário específico pelo ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> getUser(@PathVariable int id) {
        Optional<UsuarioEntity> usuario = repository.findById(id);

        return usuario.isPresent()
                ? ResponseEntity.status(200).body(usuario.get())
                : ResponseEntity.status(204).build();
    }


    @Operation(summary = "Cadastra um usuário", method = "POST")
    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioEntity> post(@RequestBody @Valid UsuarioCadastroDTO novoUsuario) {
        if (repository.existsByEmail(novoUsuario.getEmail())) {
            return ResponseEntity.status(409).build();
        }

        UsuarioEntity user = novoUsuario.convert();
        user.setPlano(0);
        repository.save(user);

        return ResponseEntity.status(201).body(user);
    }

    @Operation(summary = "Deleta um usuário", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }

    @Operation(summary = "Atualiza os dados de um usuário", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> put(@RequestBody UsuarioUpdateDTO usuarioAtualizado, @PathVariable int id) {
        if (repository.existsById(id)) {
            UsuarioEntity usuario = repository.findById(id).get();
            if (usuarioAtualizado.getSenha() == null) {
                usuario.setNome(usuarioAtualizado.getNome());
                usuario.setEmail(usuarioAtualizado.getEmail());
                usuario.setId(id);
                repository.save(usuario);
                return ResponseEntity.status(200).build();

            } else {
                usuario.setNome(usuarioAtualizado.getNome());
                usuario.setEmail(usuarioAtualizado.getEmail());
                usuario.setSenha(new BCryptPasswordEncoder().encode(usuarioAtualizado.getSenha()));
                usuario.setId(id);
                repository.save(usuario);
                return ResponseEntity.status(200).build();
            }
        }

        return ResponseEntity.status(404).build();
    }

}
