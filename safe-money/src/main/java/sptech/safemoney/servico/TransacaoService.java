package sptech.safemoney.servico;

import org.springframework.stereotype.Service;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.repositorio.ContaRepository;
import sptech.safemoney.repositorio.TransacaoRepository;

@Service
public class TransacaoService {
    private TransacaoRepository repositoryTransacao;
    private ContaRepository repositoryConta;

    public TransacaoService(TransacaoRepository repositoryTransacao, ContaRepository repositoryConta) {
        this.repositoryTransacao = repositoryTransacao;
        this.repositoryConta = repositoryConta;
    }

    public void verificarTransacao(Transacao t) {
        if (t.getTipo().getId() == 1) {
            despesa(t);
        } else if (t.getTipo().getId() == 2) {
            System.out.println("oi");
        } else if (t.getTipo().getId() == 3) {
            System.out.println("oi");
        } else if (t.getTipo().getId() == 4) {
            System.out.println("oi");
        } else if (t.getTipo().getId() == 5) {
            System.out.println("oi");
        } else if (t.getTipo().getId() == 6) {
            System.out.println("oi");
        }
    }

    public void despesa(Transacao t) {
        double saldoAtual = repositoryConta.buscarSaldoAtual(t.getConta().getId());
        t.setSaldoAnterior(saldoAtual);
        repositoryTransacao.save(t);

        repositoryConta.descontarSaldo(t.getValor(), t.getConta().getId());
    }
}
