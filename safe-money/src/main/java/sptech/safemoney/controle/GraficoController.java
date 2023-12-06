package sptech.safemoney.controle;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.dto.res.GastoPorDiaDTO;
import sptech.safemoney.dto.res.GraficoLinhaKpiDTO;
import sptech.safemoney.dto.res.GraficoPizzaDTO;
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
    @GetMapping("/gastos-por-categoria/{id}")
    public ResponseEntity <List<GraficoPizzaDTO>> transacoesOrderyByData(@PathVariable int id) {
        List<GraficoPizzaDTO> listaTransacoes = serviceGrafico.getGraficoPizzaDTO(id);

        return listaTransacoes.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaTransacoes);
    }


    @Operation(summary = "Últimos lançamentos", method = "PUT")
    @GetMapping("/top5-gastos/{id}")
    public ResponseEntity<List<Object>> top5CategoriasMaisGasto(@PathVariable int id) {
        List<Object> listaTransacoes = repositoryTransacao.top5CategoriasMaisGasto(id);

        return listaTransacoes.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(listaTransacoes);
    }


    @Operation(summary = "Lista as transacoes agrupadas por dia", method = "GET")
    @GetMapping("/grafico-linha-conta/{idConta}")
    public ResponseEntity<List<GastoPorDiaDTO>> getGastosPorDia(@PathVariable int idConta) {
        List<GastoPorDiaDTO> gastos = serviceGrafico.getGastoPorDia(idConta);

        return ResponseEntity.ok(gastos);
    }


    @Operation(summary = "Lista as transacoes agrupadas por dia", method = "GET")
    @GetMapping("/grafico-linha-geral/{idUser}")
    public ResponseEntity<List<GastoPorDiaDTO>> getGastosPorDiaGeral(@PathVariable int idUser) {
        List<GastoPorDiaDTO> gastos = serviceGrafico.getGastoPorDiaGeral(idUser);

        return ResponseEntity.ok(gastos);
    }

    @GetMapping("/grafico-linha-kpi/{idUser}")
    public ResponseEntity<GraficoLinhaKpiDTO> getKpiGraficoLinha(@PathVariable int idUser) {
        GraficoLinhaKpiDTO gastos = serviceGrafico.getKpiGraficoLinha(idUser);

        return ResponseEntity.ok(gastos);
    }

}

