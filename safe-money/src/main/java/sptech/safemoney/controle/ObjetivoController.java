package sptech.safemoney.controle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sptech.safemoney.dominio.ContaEntity;
import sptech.safemoney.dominio.Objetivo;
import sptech.safemoney.repositorio.ContaRepository;
import sptech.safemoney.repositorio.ObjetivoRepository;
import sptech.safemoney.servico.ContaService;

import java.util.List;
import java.util.Optional;

@Tag(name = "Objetivo Controller", description = "CRUD de objetivos")
@RestController
@RequestMapping("/objetivos")
public class ObjetivoController {
    @Autowired
    ObjetivoRepository repository;
    @Operation(summary = "Busca e Lista todos as contas salvas", method = "GET")
    @GetMapping("/")
    public ResponseEntity<List<Objetivo>> getObjetivos() {
        List<Objetivo> listaContas = repository.findAll();

        return listaContas.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaContas);
    }

    @Operation(summary = "Busca e lista uma conta específica pelo ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<Objetivo> getObjetivo(@PathVariable int id) {
        Optional<Objetivo> objetivo = repository.findById(id);

        return objetivo.isPresent()
                ? ResponseEntity.status(200).body(objetivo.get())
                : ResponseEntity.status(204).build();
    }

    @Operation(summary = "Busca e lista objetivos associados a um usuário específico", method = "GET")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Objetivo>> getObjetivosByUserId(@PathVariable int idUsuario) {
        List<Objetivo> objetivos = repository.findByUserId(idUsuario);

        return objetivos.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(objetivos);
    }


    @Operation(summary = "Busca e lista uma conta específica pelo ID", method = "GET")
    @GetMapping("saldoTotal/{id}")
    public ResponseEntity<Double> getSaldoTotal(@PathVariable int id) {
        Double saldo = repository.saldoTotal(id);

        if (saldo == null || Double.compare(saldo, 0.0) == 0) {
            return ResponseEntity.status(204).build(); // Retorna status 204 se for zero ou null
        } else {
            return ResponseEntity.status(200).body(saldo); // Retorna o saldo se for diferente de zero ou null
        }
    }

    @Operation(summary = "Busca e lista uma conta específica pelo ID", method = "GET")
    @GetMapping("proximoObjetivo/{id}")
    public ResponseEntity<Objetivo> objetivoMaisProximo(@PathVariable int id) {
        Objetivo objetivo = repository.objetivoProximos(id);

        if (objetivo != null) {
            return ResponseEntity.status(200).body(objetivo);
        } else {
            return ResponseEntity.status(204).build();
        }
    }

    @Operation(summary = "Cadastra uma conta", method = "POST")
    @PostMapping("/")
    public ResponseEntity<Objetivo> post(@RequestBody @Valid Objetivo novaConta) {
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
    public ResponseEntity<Objetivo> put(@RequestBody @Valid Objetivo contaAtualizada, @PathVariable int id) {
        if (repository.existsById(id)) {
            contaAtualizada.setId(id);
            repository.save(contaAtualizada);
            return ResponseEntity.status(200).body(contaAtualizada);
        }
        return ResponseEntity.status(404).build();
    }

    @Operation(summary = "Atualiza o valor investido pelo usuário", method = "PUT")
    @Transactional
    @PutMapping("/{idObjetivo}/{novoValorInvestido}/{idUsuario}")
    public ResponseEntity<Void> atualizarValor(@PathVariable int idObjetivo, @PathVariable double novoValorInvestido, @PathVariable int idUsuario) {
        if (repository.existsById(idObjetivo)) {
            repository.atualizarValorInvestido(idObjetivo, novoValorInvestido, idUsuario);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @Operation(summary = "Adiciona o valor investido pelo usuário", method = "PUT")
    @PutMapping("/adicionar/{idObjetivo}/{novoValorInvestido}/{idUsuario}")
    public ResponseEntity<Void> adicionarValor(@PathVariable int idObjetivo, @PathVariable double novoValorInvestido, @PathVariable int idUsuario) {
        if (repository.existsById(idObjetivo)) {
            repository.adicionarValorInvestido(idObjetivo, novoValorInvestido, idUsuario);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/iniciados/{id}")
    public ResponseEntity<Integer> objetivosIniciados(@PathVariable int id){
         return ResponseEntity.status(200).body(repository.iniciados(id));
    }
}


