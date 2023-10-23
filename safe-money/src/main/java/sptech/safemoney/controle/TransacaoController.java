package sptech.safemoney.controle;

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

    @GetMapping("/")
    public ResponseEntity<List<Transacao>> getAllUsers() {
        List<Transacao> listaUsuarios = repository.findAll();



        return listaUsuarios.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaUsuarios);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Transacao> getUser(@PathVariable int id) {
        Optional<Transacao> usuario = repository.findById(id);

        return usuario.isPresent()
                ? ResponseEntity.status(200).body(usuario.get())
                : ResponseEntity.status(204).build();
    }



    @PostMapping("/")
    public ResponseEntity<Transacao> post(@RequestBody @Valid Transacao novoUsuario) {
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
    public ResponseEntity<Transacao> put(@RequestBody @Valid Transacao usuarioAtualizado, @PathVariable int id) {
        if (repository.existsById(id)) {
            usuarioAtualizado.setId(id);
            repository.save(usuarioAtualizado);
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        return ResponseEntity.status(404).build();
    }


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

    @GetMapping("/leCsv")
    public ResponseEntity leCSV() {
        GerenciadorDeArquivo.leArquivoCsv("Lançamentos");

        return ResponseEntity.status(200).build();
    }

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
