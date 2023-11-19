package sptech.safemoney.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
