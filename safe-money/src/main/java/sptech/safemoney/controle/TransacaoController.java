package sptech.safemoney.controle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.safemoney.dominio.Categoria;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.dto.res.GastoPorDiaDTO;
import sptech.safemoney.repositorio.CategoriaRepository;
import sptech.safemoney.repositorio.TransacaoRepository;
import sptech.safemoney.servico.TransacaoService;
import sptech.safemoney.utils.GerenciadorDeArquivo;
import sptech.safemoney.utils.ListaObj;
import sptech.safemoney.utils.OrdenacaoPesquisa;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

@Tag(name = "Transacao Controller", description = "CRUD de transações")
@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    TransacaoRepository repository;

    @Autowired
    TransacaoService service;

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

    @Operation(summary = "Lista as últimas transações do usuário", method = "GET")
    @GetMapping("/listar-gastos/{id}")
    public ResponseEntity<List<Transacao>> listarUltimosGastos(@PathVariable int id) {
        List<Transacao> transacoes = service.listarUltimosGastos(id);

        return ResponseEntity.ok(transacoes);
    }

    @Operation(summary = "Lista as últimas transações de uma conta em específica", method = "GET")
    @GetMapping("/listar-gastos-conta/{id}")
    public ResponseEntity<List<Transacao>> listarUltimosGastosConta(@PathVariable int id) {
        List<Transacao> transacoes = service.listarUltimosGastosConta(id);

        return ResponseEntity.ok(transacoes);
    }

    @Operation(summary = "Lista as transacoes agrupadas por dia", method = "GET")
    @GetMapping("/listar-gasto-dia/{idConta}")
    public ResponseEntity<List<GastoPorDiaDTO>> getGastosPorDia(@PathVariable int idConta) {
        List<GastoPorDiaDTO> gastos = service.getGastoPorDia(idConta);

        return ResponseEntity.ok(gastos);
    }

    @Operation(summary = "Cadastra uma despesa", method = "POST")
    @PostMapping("/despesa")
    public ResponseEntity<Transacao> postDespesa(@RequestBody @Valid Transacao novaTransacao) {
        service.despesa(novaTransacao);

        return ResponseEntity.status(201).body(novaTransacao);
    }

    @Operation(summary = "Cadastra uma despesa", method = "POST")
    @PostMapping("/despesa-credito")
    public ResponseEntity<Transacao> postDespesaCredito(@RequestBody @Valid Transacao novaTransacao) {
        service.despesaCredito(novaTransacao);

        return ResponseEntity.status(201).body(novaTransacao);
    }

    @Operation(summary = "Cadastra uma receita", method = "POST")
    @PostMapping("/receita")
    public ResponseEntity<Transacao> postReceita(@RequestBody @Valid Transacao novaTransacao) {
        service.receita(novaTransacao);

        return ResponseEntity.status(201).body(novaTransacao);
    }


    @PostMapping("/transferencia/{idRemetente}")
    public ResponseEntity<Transacao> postTransferencia(@RequestBody @Valid Transacao novaTransacao, @PathVariable int idRemetente) {
        service.transferencia(novaTransacao, idRemetente);
      
    @Operation(summary = "Cadastra uma transação", method = "POST")
    @PostMapping("/")
    public ResponseEntity<Transacao> post(@RequestBody @Valid Transacao novaTransacao) {
        service.verificarTransacao(novaTransacao);

        return ResponseEntity.status(201).body(novaTransacao);
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
