package sptech.safemoney.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.safemoney.dto.res.GraficoPrevistoDTO;
import sptech.safemoney.repositorio.LancamentosFixosRepository;
import sptech.safemoney.repositorio.TransacaoRepository;

import java.time.LocalDate;

@Service
public class GraficoService {
    @Autowired
    private TransacaoRepository repositoryTransacao;
    @Autowired
    private LancamentosFixosRepository repositoryLancamentoFixo;

    public GraficoPrevistoDTO fixoProximoMes(int idUsuario){
        LocalDate dataEspecifica = LocalDate.now().plusMonths(1);
        double receitaFixa = repositoryLancamentoFixo.receitaPrevista(idUsuario);

        double despesaFixa = repositoryLancamentoFixo.despesaPrevistaFixa(idUsuario);
        double despesaCartao = repositoryTransacao.despesaPrevistaCartao(idUsuario, dataEspecifica);
        double despesaTotal = despesaFixa + despesaCartao;

        double saldo = receitaFixa - despesaTotal;

        GraficoPrevistoDTO graficoPrevisto = new GraficoPrevistoDTO(receitaFixa, despesaTotal, saldo);

        return graficoPrevisto;
    }
}
