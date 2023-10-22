package sptech.safemoney.controle;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.safemoney.dominio.CartaoCredito;
import sptech.safemoney.dominio.Usuario;
import sptech.safemoney.repositorio.CartaoCreditoRepository;
import sptech.safemoney.repositorio.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cartaoCredito")
public class CartaoCreditoController {

    @Autowired
    CartaoCreditoRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<CartaoCredito>> getAllUsers() {
        List<CartaoCredito> listaUsuarios = repository.findAll();

        return listaUsuarios.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaUsuarios);
    }






    @GetMapping("/{id}")
    public ResponseEntity<CartaoCredito> getUser(@PathVariable int id) {
        Optional<CartaoCredito> usuario = repository.findById(id);

        return usuario.isPresent()
                ? ResponseEntity.status(200).body(usuario.get())
                : ResponseEntity.status(204).build();
    }


    @PostMapping("/")
    public ResponseEntity<CartaoCredito> post(@RequestBody @Valid CartaoCredito novoCartao) {
        if (repository.existsById(novoCartao.getId())) {
            return ResponseEntity.status(409).build();
        }
        repository.save(novoCartao);
        return ResponseEntity.status(201).body(novoCartao);
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
    public ResponseEntity<CartaoCredito> put(@RequestBody @Valid CartaoCredito usuarioAtualizado, @PathVariable int id) {
        if (repository.existsById(id)) {
            usuarioAtualizado.setId(id);
            repository.save(usuarioAtualizado);
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        return ResponseEntity.status(404).build();
    }

}
