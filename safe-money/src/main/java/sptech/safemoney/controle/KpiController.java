package sptech.safemoney.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.safemoney.dominio.ContaEntity;
import sptech.safemoney.dto.res.ResumoKpiDTO;
import sptech.safemoney.servico.KpiService;

@RestController
@RequestMapping("/kpi")
public class KpiController {

    @Autowired
    private KpiService service;

    @GetMapping("/resumo/{id}")
    public ResponseEntity<ResumoKpiDTO> getResumo(@PathVariable int id) {
        ResumoKpiDTO resumoDto = service.getResumo(id);

        return ResponseEntity.ok(resumoDto);
    }

    @GetMapping("/resumo-conta/{idConta}")
    public ResponseEntity<ResumoKpiDTO> getResumoConta(@PathVariable int idConta) {
        ResumoKpiDTO resumoDto = service.getResumoConta(idConta);

        return ResponseEntity.ok(resumoDto);
    }
}
