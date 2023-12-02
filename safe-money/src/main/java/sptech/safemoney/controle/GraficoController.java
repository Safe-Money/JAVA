package sptech.safemoney.controle;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.dto.res.GraficoPrevistoDTO;
import sptech.safemoney.repositorio.TransacaoRepository;
import sptech.safemoney.servico.GraficoService;

import java.util.List;

@RestController
@RequestMapping("/graficos")
public class GraficoController {
    @Autowired
    GraficoService serviceGrafico;

    @Autowired
    TransacaoRepository repositoryTransacao;

    @GetMapping("/previsto/{id}")
    public ResponseEntity<GraficoPrevistoDTO> receitaPrevista(@PathVariable int id) {
        GraficoPrevistoDTO graficoPrevisto = serviceGrafico.fixoProximoMes(id);

        return ResponseEntity.ok(graficoPrevisto);
    }

    @Operation(summary = "Últimos lançamentos", method = "PUT")
    @GetMapping("/gastosPorCategoria/{id}")
    public ResponseEntity <List<Object>> transacoesOrderyByData(@RequestBody @Valid Transacao usuarioAtualizado, @PathVariable int id) {
        List<Object> listaTransacoes = repositoryTransacao.graficoGastosPorcategoria(id);

        return listaTransacoes.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaTransacoes);
    }


    @Operation(summary = "Últimos lançamentos", method = "PUT")
    @GetMapping("/top5catogoriasPorGasto/{id}")
    public ResponseEntity <List<Object>> top5CategoriasMaisGasto(@PathVariable int id) {
        List<Object> listaTransacoes = repositoryTransacao.top5CategoriasMaisGasto(id);

        return listaTransacoes.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaTransacoes);
    }


}

