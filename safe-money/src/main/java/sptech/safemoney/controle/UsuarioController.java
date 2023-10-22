package sptech.safemoney.controle;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.safemoney.dominio.UsuarioEntity;
import sptech.safemoney.dto.UsuarioCadastroDTO;
import sptech.safemoney.repositorio.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<UsuarioEntity>> getAllUsers() {
        List<UsuarioEntity> listaUsuarios = repository.findAll();

        return listaUsuarios.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaUsuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> getUser(@PathVariable int id) {
        Optional<UsuarioEntity> usuario = repository.findById(id);

        return usuario.isPresent()
                ? ResponseEntity.status(200).body(usuario.get())
                : ResponseEntity.status(204).build();
    }

    @PostMapping("/")
    public ResponseEntity<UsuarioEntity> post(@RequestBody @Valid UsuarioCadastroDTO novoUsuario) {
        if (repository.existsByEmail(novoUsuario.getEmail())) {
            return ResponseEntity.status(409).build();
        }

        UsuarioEntity user = novoUsuario.convert();
        repository.save(user);

        return ResponseEntity.status(201).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> put(@RequestBody @Valid UsuarioEntity usuarioAtualizado, @PathVariable int id) {
        if (repository.existsById(id)) {
            usuarioAtualizado.setId(id);
            repository.save(usuarioAtualizado);
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        return ResponseEntity.status(404).build();
    }

}
