package sptech.safemoney.controle;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.safemoney.dominio.Categoria;
import sptech.safemoney.repositorio.CategoriaRepository;

import java.util.List;
import java.util.Optional;

@Tag(name = "Categoria Controller", description = "CRUD de categorias")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<Categoria>> getAllUsers() {
        List<Categoria> listaUsuarios = repository.findAll();

        return listaUsuarios.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaUsuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getUser(@PathVariable int id) {
        Optional<Categoria> usuario = repository.findById(id);

        return usuario.isPresent()
                ? ResponseEntity.status(200).body(usuario.get())
                : ResponseEntity.status(204).build();
    }


    @PostMapping("/")
    public ResponseEntity<Categoria> post(@RequestBody @Valid Categoria novoUsuario) {
        if (repository.existsById(novoUsuario.getId())) {
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
    public ResponseEntity<Categoria> put(@RequestBody @Valid Categoria usuarioAtualizado, @PathVariable int id) {
        if (repository.existsById(id)) {
            usuarioAtualizado.setId(id);
            repository.save(usuarioAtualizado);
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        return ResponseEntity.status(404).build();
    }

}
