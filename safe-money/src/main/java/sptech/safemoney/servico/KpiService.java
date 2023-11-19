package sptech.safemoney.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.safemoney.dominio.ContaEntity;
import sptech.safemoney.repositorio.ContaRepository;
import sptech.safemoney.repositorio.FaturaRepository;

@Service
public class KpiService {
    @Autowired
    private ContaRepository repositoryConta;

    @Autowired
    private FaturaRepository repositoryFatura;

    public double buscarSaldoTotal(int idUsuario){
        double saldoTotal = repositoryConta.saldoTotal(idUsuario);

        return saldoTotal;
    }

    public double buscarDespesaTotal(int idUsuario){
        double despesaTotal = repositoryConta.despesaTotal(idUsuario);

        return despesaTotal;
    }

    public double buscarFaturaTotal(int idUsuario){
        double faturaTotal = repositoryFatura.faturaTotal(idUsuario);

        return faturaTotal;
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
