package sptech.safemoney.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.safemoney.dominio.ContaEntity;
import sptech.safemoney.dto.res.ResumoKpiDTO;
import sptech.safemoney.repositorio.ContaRepository;
import sptech.safemoney.repositorio.FaturaRepository;

@Service
public class KpiService {
    @Autowired
    private ContaRepository repositoryConta;

    @Autowired
    private FaturaRepository repositoryFatura;

    public ResumoKpiDTO getResumo(int idUsuario) {
        Double saldoTotal = repositoryConta.saldoTotal(idUsuario);
        Double despesaTotal = repositoryConta.despesaTotal(idUsuario);
        Double faturaTotal = repositoryFatura.faturaTotal(idUsuario);
        if (faturaTotal == null) {
            faturaTotal = 0.0;
        }

        ResumoKpiDTO resumoDto = new ResumoKpiDTO(saldoTotal, despesaTotal, faturaTotal);

        return resumoDto;
    }


    public ResumoKpiDTO getResumoConta(int idConta) {
        Double saldoTotal = repositoryConta.saldoTotalConta(idConta);
        Double despesaTotal = repositoryConta.despesaTotalConta(idConta);
        Double faturaTotal = repositoryFatura.faturaTotalConta(idConta);
        if (faturaTotal == null) {
            faturaTotal = 0.0;
        }

        ResumoKpiDTO resumoDto = new ResumoKpiDTO(saldoTotal, despesaTotal, faturaTotal);

        return resumoDto;
    }


    public double buscarSaldoTotalConta(int idUsuario){
        double saldoTotal = repositoryConta.saldoTotalConta(idUsuario);

        return saldoTotal;
    }

    public double buscarDespesaTotalConta(int idUsuario){
        double despesaTotal = repositoryConta.despesaTotalConta(idUsuario);

        return despesaTotal;
    }

    public double buscarFaturaTotalConta(int idUsuario){
        double faturaTotal = repositoryFatura.faturaTotalConta(idUsuario);

        return faturaTotal;
    }
}
