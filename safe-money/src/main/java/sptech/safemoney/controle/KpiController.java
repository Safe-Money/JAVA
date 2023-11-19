package sptech.safemoney.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sptech.safemoney.dominio.ContaEntity;
import sptech.safemoney.servico.KpiService;

@RestController
@RequestMapping("/kpi")
public class KpiController {

    @Autowired
    private KpiService service;

    @GetMapping("/saldo-total/{id}")
    public ResponseEntity<Double> getSaldoTotal(@PathVariable int id) {
        double saldoTotal = service.buscarSaldoTotal(id);

        return ResponseEntity.ok(saldoTotal);
    }

    @GetMapping("/despesa-total/{id}")
    public ResponseEntity<Double> getDespesaTotal(@PathVariable int id) {
        double despesaTotal = service.buscarDespesaTotal(id);

        return ResponseEntity.ok(despesaTotal);
    }

    @GetMapping("/fatura-total/{id}")
    public ResponseEntity<Double> getFaturaTotal(@PathVariable int id) {
        double despesaTotal = service.buscarFaturaTotal(id);

        return ResponseEntity.ok(despesaTotal);
    }



    @GetMapping("/saldo-total-conta/{idConta}")
    public ResponseEntity<Double> getSaldoTotalConta(@PathVariable int idConta) {
        double saldoTotal = service.buscarSaldoTotalConta(idConta);

        return ResponseEntity.ok(saldoTotal);
    }

    @GetMapping("/despesa-total-conta/{idConta}")
    public ResponseEntity<Double> getDespesaTotalConta(@PathVariable int idConta) {
        double despesaTotal = service.buscarDespesaTotalConta(idConta);

        return ResponseEntity.ok(despesaTotal);
    }

    @GetMapping("/fatura-total-conta/{idConta}")
    public ResponseEntity<Double> getFaturaTotalConta(@PathVariable int idConta) {
        double despesaTotal = service.buscarFaturaTotalConta(idConta);

        return ResponseEntity.ok(despesaTotal);
    }
}
