package sptech.safemoney.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.safemoney.dto.res.GastoPorDiaDTO;
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

    public List<GraficoPizzaDTO> getGraficoPizzaDTO(int id) {
        return repositoryTransacao.graficoGastosPorcategoria(id);
    }
}
