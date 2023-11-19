package sptech.safemoney.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.safemoney.dto.res.GraficoPrevistoDTO;
import sptech.safemoney.servico.GraficoService;

@RestController
@RequestMapping("/graficos")
public class GraficoController {
    @Autowired
    GraficoService serviceGrafico;

    @GetMapping("/previsto/{id}/{mes}/{ano}")
    public ResponseEntity<GraficoPrevistoDTO> receitaPrevista(@PathVariable int id) {
        GraficoPrevistoDTO graficoPrevisto = serviceGrafico.fixoProximoMes(id);

        return ResponseEntity.ok(graficoPrevisto);
    }


}
