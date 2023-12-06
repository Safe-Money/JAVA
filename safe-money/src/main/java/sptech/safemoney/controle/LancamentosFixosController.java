package sptech.safemoney.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.safemoney.dominio.LancamentosFixos;
import sptech.safemoney.servico.LancamentosFixosService;

import java.util.List;

@RestController
@RequestMapping("/lancamento-fixo")
public class LancamentosFixosController {
    @Autowired
    LancamentosFixosService service;

    @GetMapping("/buscar-fixos/{idConta}")
    public ResponseEntity<List<LancamentosFixos>> getBuscarFixos(@PathVariable int idConta) {
        List<LancamentosFixos> lancamentos = service.buscarLancamentosFixos(idConta);

        return ResponseEntity.ok(lancamentos);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Void> postFixo(@RequestBody LancamentosFixos novoL) {
        service.cadastrarLancamento(novoL);

        return ResponseEntity.status(201).build();
    }

    @GetMapping("/desfazer-lancamento-fixo")
    public ResponseEntity<Void> desfazerLancamento() {
        service.desfazerLanc();

        return ResponseEntity.status(200).build();
    }
}
