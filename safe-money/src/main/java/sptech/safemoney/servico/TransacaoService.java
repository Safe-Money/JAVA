package sptech.safemoney.servico;

import org.springframework.stereotype.Service;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.repositorio.ContaRepository;
import sptech.safemoney.repositorio.FaturaRepository;
import sptech.safemoney.repositorio.TransacaoRepository;
import sptech.safemoney.utils.ListaObj;
import sptech.safemoney.utils.OrdenacaoPesquisa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {
    private TransacaoRepository repositoryTransacao;
    private ContaRepository repositoryConta;
    private FaturaRepository repositoryFatura;

    public TransacaoService(TransacaoRepository repositoryTransacao, ContaRepository repositoryConta, FaturaRepository repositoryFatura) {
        this.repositoryTransacao = repositoryTransacao;
        this.repositoryConta = repositoryConta;
        this.repositoryFatura = repositoryFatura;
    }

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
}
