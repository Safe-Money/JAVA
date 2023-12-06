package sptech.safemoney.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.dto.res.GastoPorDiaDTO;
import sptech.safemoney.dto.res.GraficoLinhaKpiDTO;
import sptech.safemoney.dto.res.GraficoPizzaDTO;
import sptech.safemoney.dto.res.GraficoPrevistoDTO;
import sptech.safemoney.repositorio.LancamentosFixosRepository;
import sptech.safemoney.repositorio.TransacaoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class GraficoService {
    @Autowired
    private TransacaoRepository repositoryTransacao;
    @Autowired
    private LancamentosFixosRepository repositoryLancamentoFixo;

    public GraficoPrevistoDTO fixoProximoMes(int idUsuario){
        LocalDate dataEspecifica = LocalDate.now().plusMonths(1);
        Double receitaFixa = repositoryLancamentoFixo.receitaPrevista(idUsuario);
        if (receitaFixa == null) {
            receitaFixa = 0.0;
        }

        Double despesaFixa = repositoryLancamentoFixo.despesaPrevistaFixa(idUsuario);
        if (despesaFixa == null) {
            despesaFixa = 0.0;
        }

        Double despesaCartao = repositoryTransacao.despesaPrevistaCartao(idUsuario, dataEspecifica);
        if (despesaCartao == null) {
            despesaCartao = 0.0;
        }
        double despesaTotal = despesaFixa + despesaCartao;

        double saldo = receitaFixa - despesaTotal;

        GraficoPrevistoDTO graficoPrevisto = new GraficoPrevistoDTO(receitaFixa, despesaTotal, saldo);

        return graficoPrevisto;
    }

    public List<GastoPorDiaDTO> getGastoPorDia(int idConta) {
        return repositoryTransacao.getGastoPorDia(idConta);
    }

    public List<GastoPorDiaDTO> getGastoPorDiaGeral(int idUser) {
        return repositoryTransacao.getGastoPorDiaGeral(idUser);
    }

    public GraficoLinhaKpiDTO getKpiGraficoLinha(int idUser) {
        List<GastoPorDiaDTO> lista = repositoryTransacao.getGastoPorDiaGeral(idUser);

        int n = lista.size();

        double variacaoPercentual = ((lista.get(n -1).getValor() - lista.get(0).getValor()) / lista.get(0).getValor()) * 100;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (lista.get(j).getValor() > (lista.get(j + 1).getValor())) {
                    // Troca os elementos se estiverem na ordem errada
                    GastoPorDiaDTO gasto = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, gasto);
                }
            }
        }

        double menorValor = lista.get(0).getValor();
        double maiorValor = lista.get(n - 1).getValor();

        return new GraficoLinhaKpiDTO(menorValor, maiorValor, variacaoPercentual);
    }

    public List<GraficoPizzaDTO> getGraficoPizzaDTO(int id) {
        return repositoryTransacao.graficoGastosPorcategoria(id);
    }
}
