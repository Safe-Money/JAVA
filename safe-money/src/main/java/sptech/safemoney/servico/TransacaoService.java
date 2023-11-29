package sptech.safemoney.servico;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sptech.safemoney.dominio.Fatura;
import sptech.safemoney.dominio.LancamentosFixos;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.dto.mapper.TransacaoDespesaCreditoMapper;
import sptech.safemoney.dto.req.DespesaCreditoDTO;
import sptech.safemoney.dto.res.GastoPorDiaDTO;
import sptech.safemoney.repositorio.CartaoCreditoRepository;
import sptech.safemoney.repositorio.ContaRepository;
import sptech.safemoney.repositorio.FaturaRepository;
import sptech.safemoney.repositorio.TransacaoRepository;
import sptech.safemoney.utils.ListaObj;
import sptech.safemoney.utils.OrdenacaoPesquisa;
import sptech.safemoney.utils.PilhaObj;

import java.time.LocalDate;
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
    private TransacaoDespesaCreditoMapper mapper;

    public TransacaoService() {
        this.mapper = new TransacaoDespesaCreditoMapper();
    }

    private PilhaObj<Transacao> pilha;

    @PostConstruct
    public void iniciarPilha() {
        pilha = new PilhaObj<>(10);
    }

    public void despesa(Transacao t) {
        double saldoAtual = repositoryConta.buscarSaldoAtual(t.getConta().getId());
        t.setSaldoAnterior(saldoAtual);
        repositoryTransacao.save(t);
        pilha.push(t);

        repositoryConta.descontarSaldo(t.getValor(), t.getConta().getId());
    }

    public void despesaCredito(DespesaCreditoDTO novaDespesa) {
        List<Fatura> faturas = repositoryFatura.getFaturasAbertas(LocalDate.now(), novaDespesa.getCartao().getId());
        double limiteAtual = 10;

        Transacao t = null;
        for (int i = 1; i <= novaDespesa.getParcelas(); i++) {
            novaDespesa.setFatura(faturas.get(i - 1));
            novaDespesa.setSaldoAnterior(limiteAtual);
            novaDespesa.setParcelaAtual(i);
            t = mapper.paraEntidade(novaDespesa);
            repositoryTransacao.save(t);
        }

        repositoryFatura.atualizarFatura(t.getValor(), novaDespesa.getCartao().getId());
    }

    public void desfazerCredito() {
        if (pilha.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não há ações anteriores para desfazer.");
        }

        Transacao t = pilha.pop();
        repositoryTransacao.deleteById(t.getId());
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
