package sptech.safemoney.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.safemoney.dominio.LancamentosFixos;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.dto.res.GastoPorDiaDTO;
import sptech.safemoney.repositorio.CartaoCreditoRepository;
import sptech.safemoney.repositorio.ContaRepository;
import sptech.safemoney.repositorio.FaturaRepository;
import sptech.safemoney.repositorio.TransacaoRepository;
import sptech.safemoney.utils.ListaObj;
import sptech.safemoney.utils.OrdenacaoPesquisa;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository repositoryTransacao;
    @Autowired
    private ContaRepository repositoryConta;
    @Autowired
    private FaturaRepository repositoryFatura;
    @Autowired
    private CartaoCreditoRepository repositoryCartao;

    public void despesa(Transacao t) {
        double saldoAtual = repositoryConta.buscarSaldoAtual(t.getConta().getId());
        t.setSaldoAnterior(saldoAtual);
        repositoryTransacao.save(t);

        repositoryConta.descontarSaldo(t.getValor(), t.getConta().getId());
    }

    public void despesaCredito(Transacao t) {
        int fkCartao = repositoryFatura.buscarFkCartao(t.getFatura().getId());
        double limiteAtual = repositoryFatura.buscarLimiteAtual(fkCartao);
        t.setSaldoAnterior(limiteAtual);
        repositoryTransacao.save(t);
        repositoryFatura.atualizarFatura(t.getValor(), fkCartao);
    }

    public void receita(Transacao t) {
        double saldoAtual = repositoryConta.buscarSaldoAtual(t.getConta().getId());
        t.setSaldoAnterior(saldoAtual);
        repositoryTransacao.save(t);

        repositoryConta.acrescentarSaldo(t.getValor(), t.getConta().getId());
    }

    public void transferencia(Transacao t , int idRemetente) {
        double saldoAtual = repositoryConta.buscarSaldoAtual(t.getConta().getId());
        t.setSaldoAnterior(saldoAtual);
        repositoryTransacao.save(t);

        repositoryConta.descontarSaldo(t.getValor(), t.getConta().getId());
        repositoryConta.acrescentarSaldo(t.getValor(), idRemetente);
    }

    public List<Transacao> listarUltimosGastos(int idUsuario) {
        List<Transacao> gastosCredito = repositoryTransacao.getUltimosGastosCredito(idUsuario);
        List<Transacao> gastosDebito = repositoryTransacao.getUltimosGastosDebito(idUsuario);

        ListaObj<Transacao> gastosTotal = new ListaObj<>((gastosCredito.size() + gastosDebito.size()));
        for (Transacao t : gastosCredito) {
            gastosTotal.adiciona(t);
        }

        for (Transacao t : gastosDebito) {
            gastosTotal.adiciona(t);
        }

        OrdenacaoPesquisa.ordernacaoBubbleSort(gastosTotal);

        List<Transacao> gastosFinal = new ArrayList<>();
        for (int i = 0; i < gastosTotal.getTamanho(); i++) {
            gastosFinal.add(gastosTotal.getElemento(i));
        }

        return gastosFinal;
    }

    // Tela de conta

    public List<Transacao> listarUltimosGastosConta(int idConta, int mes) {
        List<Transacao> gastosCredito = repositoryTransacao.getUltimosGastosCreditoConta(idConta, mes);
        List<Transacao> gastosDebito = repositoryTransacao.getUltimosGastosDebitoConta(idConta, mes);

        ListaObj<Transacao> gastosTotal = new ListaObj<>((gastosCredito.size() + gastosDebito.size()));
        for (Transacao t : gastosCredito) {
            gastosTotal.adiciona(t);
        }

        for (Transacao t : gastosDebito) {
            gastosTotal.adiciona(t);
        }

        OrdenacaoPesquisa.ordernacaoBubbleSort(gastosTotal);

        List<Transacao> gastosFinal = new ArrayList<>();
        for (int i = 0; i < gastosTotal.getTamanho(); i++) {
            gastosFinal.add(gastosTotal.getElemento(i));
        }

        return gastosFinal;
    }

    public List<GastoPorDiaDTO> getGastoPorDia(int idConta) {
        return repositoryTransacao.getGastoPorDia(idConta);
    }
}
