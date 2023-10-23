package sptech.safemoney.controle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.safemoney.dominio.Categoria;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.repositorio.CategoriaRepository;
import sptech.safemoney.repositorio.TransacaoRepository;
import sptech.safemoney.utils.GerenciadorDeArquivo;
import sptech.safemoney.utils.ListaObj;
import sptech.safemoney.utils.OrdenacaoPesquisa;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

@Tag(name = "Transacao Controller", description = "CRUD de transações")
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    TransacaoRepository repository;

    @Operation(summary = "Busca e Lista todos as transações", method = "GET")
    @GetMapping("/")
    public ResponseEntity<List<Transacao>> getAllUsers() {
        List<Transacao> listaUsuarios = repository.findAll();

        return listaUsuarios.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaUsuarios);
    }

    @Operation(summary = "Busca e lista uma transação específica pelo ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<Transacao> getUser(@PathVariable int id) {
        Optional<Transacao> usuario = repository.findById(id);

        return usuario.isPresent()
                ? ResponseEntity.status(200).body(usuario.get())
                : ResponseEntity.status(204).build();
    }


    @Operation(summary = "Cadastra uma transação", method = "POST")
    @PostMapping("/")
    public ResponseEntity<Transacao> post(@RequestBody @Valid Transacao novoUsuario) {
        if (repository.existsById(novoUsuario.getId())) {
            return ResponseEntity.status(409).build();
        }

        repository.save(novoUsuario);

        return ResponseEntity.status(201).body(novoUsuario);
    }

    @Operation(summary = "Deleta uma transação", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }

    @Operation(summary = "Atualiza os dados de uma transação", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<Transacao> put(@RequestBody @Valid Transacao usuarioAtualizado, @PathVariable int id) {
        if (repository.existsById(id)) {
            usuarioAtualizado.setId(id);
            repository.save(usuarioAtualizado);
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        return ResponseEntity.status(404).build();
    }

    @Operation(summary = "Exporta para a pasta raiz do projeto um arquivo CSV com as transações do usuário indicado pelo ID", method = "GET")
    @GetMapping("/exportar/{id}")
    public ResponseEntity exportar(@PathVariable int id) {
        List<Transacao> listaTransacoes = repository.findAllTransacoesByUserId(id);

        ListaObj<Transacao> listaObj = new ListaObj<>(listaTransacoes.size());

        for (int i = 0; i < listaTransacoes.size(); i++) {
            listaObj.adiciona(listaTransacoes.get(i));
        }

        GerenciadorDeArquivo.gravaArquivoCsv(listaObj, "Lançamentos");

        return ResponseEntity.status(200).build();
    }

    @Operation(summary = "Lê o arquivo CSV na pasta raiz do projeto", method = "GET")
    @GetMapping("/leCsv")
    public ResponseEntity leCSV() {
        GerenciadorDeArquivo.leArquivoCsv("Lançamentos");

        return ResponseEntity.status(200).build();
    }


    @Operation(summary = "Busca um lançamento pela data da mesma e pelo ID do usuário", method = "GET")
    @GetMapping("/pesquisaBinaria/{id}/{data}")
    public ResponseEntity<Transacao> pesquisaBinaria(@PathVariable int id, @PathVariable LocalDate data) {
        List<Transacao> listaTransacoes = repository.findAllTransacoesByUserId(id);
        ListaObj<Transacao> listaObj = new ListaObj<>(listaTransacoes.size());

        for (int i = 0; i < listaTransacoes.size(); i++) {
            listaObj.adiciona(listaTransacoes.get(i));
        }

        OrdenacaoPesquisa.ordernacaoBubbleSort(listaObj);
        int transacaoEncontrada = OrdenacaoPesquisa.pesquisaBinaria(listaObj, data);

        return transacaoEncontrada == -1
            ? ResponseEntity.status(404).build()
            : ResponseEntity.ok(listaObj.getElemento(transacaoEncontrada));
    }


}
