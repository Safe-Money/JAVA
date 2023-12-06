package sptech.safemoney.controle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.safemoney.dominio.CartaoCredito;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.dto.res.CartaoFaturaDTO;
import sptech.safemoney.repositorio.CartaoCreditoRepository;
import sptech.safemoney.servico.CartaoCreditoService;

import java.util.List;
import java.util.Optional;

@Tag(name = "CartãoCredito Controller", description = "CRUD de cartões")
@RestController
@RequestMapping("/cartao-credito")
public class CartaoCreditoController {

    @Autowired
    CartaoCreditoRepository repository;

    @Autowired
    CartaoCreditoService service;

    @Operation(summary = "Busca e Lista todos os cartões de crédito salvos", method = "GET")
    @GetMapping("/")
    public ResponseEntity<List<CartaoCredito>> getAllUsers() {
        List<CartaoCredito> listaUsuarios = repository.findAll();

        return listaUsuarios.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaUsuarios);
    }

    @Operation(summary = "Busca e lista um cartão específico pelo ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<CartaoCredito> getUser(@PathVariable int id) {
        Optional<CartaoCredito> usuario = repository.findById(id);

        return usuario.isPresent()
                ? ResponseEntity.status(200).body(usuario.get())
                : ResponseEntity.status(204).build();
    }

    @GetMapping("/listar-cartoes/{id}")
    public ResponseEntity<List<CartaoFaturaDTO>> getCartaoCredito(@PathVariable int id) {
        List<CartaoFaturaDTO> cartoes = service.listarCartoes(id);

        return cartoes.size() == 0 ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(cartoes);
    }

    @GetMapping("/listar-cartoes-conta/{idConta}")
    public ResponseEntity<List<CartaoFaturaDTO>> getCartaoCreditoConta(@PathVariable int idConta) {
        List<CartaoFaturaDTO> cartoes = service.listarCartoesConta(idConta);

        return cartoes.size() == 0 ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(cartoes);
    }

    @GetMapping("/listar-fatura/{idCartao}/{mes}")
    public ResponseEntity<List<Transacao>> getFatura(@PathVariable int idCartao, @PathVariable int mes) {
        List<Transacao> cartoes = service.getTransacaoFatura(idCartao, mes);

        return ResponseEntity.ok(cartoes);
    }

    @Operation(summary = "Cadastra um novo cartão", method = "POST")
    @PostMapping("/")
    public ResponseEntity<CartaoCredito> post(@RequestBody @Valid CartaoCredito novoCartao) {
        service.cadastrarCartao(novoCartao);

        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Deleta um novo cartão", method = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.deletarCartao(id);

        return ResponseEntity.status(200).build();
    }

    @Operation(summary = "Atualiza os dados de um cartão", method = "PUT")
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