package sptech.safemoney.controle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.safemoney.dominio.Conta;
import sptech.safemoney.repositorio.ContaRepository;

import java.util.List;
import java.util.Optional;

@Tag(name = "Conta Controller", description = "CRUD de contas")
@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    ContaRepository repository;

    @Operation(summary = "Busca e Lista todos as contas salvas", method = "GET")

    @GetMapping("/")
    public ResponseEntity<List<Conta>> getContas() {
        List<Conta> listaContas = repository.findAll();

        return listaContas.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaContas);
    }

    @Operation(summary = "Busca e lista uma conta específica pelo ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<Conta> getUser(@PathVariable int id) {
        Optional<Conta> usuario = repository.findById(id);

        return usuario.isPresent()
                ? ResponseEntity.status(200).body(usuario.get())
                : ResponseEntity.status(204).build();
    }

    @Operation(summary = "Cadastra uma conta", method = "POST")
    @PostMapping("/")
    public ResponseEntity<Conta> post(@RequestBody @Valid Conta novaConta) {
        if (repository.existsById(novaConta.getId())) {
            return ResponseEntity.status(409).build();
        }

        repository.save(novaConta);

        return ResponseEntity.status(201).body(novaConta);
    }

    @Operation(summary = "Deleta uma conta", method = "DELETE")
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
    public ResponseEntity<Conta> put(@RequestBody @Valid Conta contaAtualizada, @PathVariable int id) {
        if (repository.existsById(id)) {
            contaAtualizada.setId(id);
            repository.save(contaAtualizada);
            return ResponseEntity.status(200).body(contaAtualizada);
        }
        return ResponseEntity.status(404).build();
    }
}

