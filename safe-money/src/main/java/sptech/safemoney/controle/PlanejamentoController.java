package sptech.safemoney.controle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.safemoney.dominio.Planejamento;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.repositorio.PlanejamentoRepository;
import sptech.safemoney.repositorio.TransacaoRepository;
import sptech.safemoney.utils.GerenciadorDeArquivo;
import sptech.safemoney.utils.ListaObj;
import sptech.safemoney.utils.OrdenacaoPesquisa;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Tag(name = "Planejamento Controller", description = "CRUD de transações")
@RestController
@RequestMapping("/planejamento")
public class PlanejamentoController {

    @Autowired
    PlanejamentoRepository repository;

    @Operation(summary = "Busca e Lista todos os planejamentos", method = "GET")
    @GetMapping("/")
    public ResponseEntity<List<Planejamento>> getAllPlanejamento() {
        List<Planejamento> listaPlanejamento = repository.findAll();

        return listaPlanejamento.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaPlanejamento);
    }




    @Operation(summary = "Cadastra um planejamento", method = "POST")
    @PostMapping("/")
    public ResponseEntity<Planejamento> post(@RequestBody @Valid Planejamento novoPlanejamento) {
        if (repository.existsById(novoPlanejamento.getId())) {
            return ResponseEntity.status(409).build();
        }

        repository.save(novoPlanejamento);

        return ResponseEntity.status(201).body(novoPlanejamento);
    }

    @Operation(summary = "Deleta um planejamento", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }

    @Operation(summary = "Atualiza os dados de um planejamento", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<Planejamento> put(@RequestBody @Valid Planejamento usuarioAtualizado, @PathVariable int id) {
        if (repository.existsById(id)) {
            usuarioAtualizado.setId(id);
            repository.save(usuarioAtualizado);
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        return ResponseEntity.status(404).build();
    }



    @Operation(summary = "Busca e lista os planejamentos específicos pelo ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity <List<Planejamento>> getPlanejamento(@PathVariable int id) {
        List<Planejamento> listaPlanejamentos = repository.findAllPlanejamentosByUserId(id);

        return listaPlanejamentos.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaPlanejamentos);
    }

    @Operation(summary = "Busca e lista os planejamentos específicos pelo ID", method = "GET")
    @GetMapping("/{id}/{mes}")
    public ResponseEntity <List<Object[]>> getPlanejamentoPorMes(@PathVariable int id, @PathVariable int mes) {
        List<Object[]> listaPlanejamentos = repository.countPlanejamentosByCategoryAndUserIdAndMonth(id, mes);

        return listaPlanejamentos.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaPlanejamentos);
    }
}