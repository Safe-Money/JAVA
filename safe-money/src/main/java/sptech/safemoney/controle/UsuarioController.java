package sptech.safemoney.controle;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.safemoney.dominio.Usuario;
import sptech.safemoney.repositorio.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> listaUsuarios = repository.findAll();

        return listaUsuarios.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaUsuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUser(@PathVariable int id) {
        Optional<Usuario> usuario = repository.findById(id);

        return usuario.isPresent()
                ? ResponseEntity.status(200).body(usuario.get())
                : ResponseEntity.status(204).build();
    }

    @PostMapping("/")
    public ResponseEntity<Usuario> post(@RequestBody @Valid Usuario novoUsuario) {
        if (repository.existsByEmail(novoUsuario.getEmail())) {
            return ResponseEntity.status(409).build();
        }

        repository.save(novoUsuario);

        return ResponseEntity.status(201).body(novoUsuario);
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
    public ResponseEntity<Usuario> put(@RequestBody @Valid Usuario usuarioAtualizado, @PathVariable int id) {
        if (repository.existsById(id)) {
            usuarioAtualizado.setId(id);
            repository.save(usuarioAtualizado);
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        return ResponseEntity.status(404).build();
    }

}
