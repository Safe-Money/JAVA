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

}
