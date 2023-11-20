package sptech.safemoney.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import sptech.safemoney.dominio.CartaoCredito;
import sptech.safemoney.dominio.Fatura;
import sptech.safemoney.dominio.Situacao;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.repositorio.CartaoCreditoRepository;
import sptech.safemoney.repositorio.FaturaRepository;
import sptech.safemoney.repositorio.SituacaoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class CartaoCreditoService {
    @Autowired
    CartaoCreditoRepository repositoryCartao;
    @Autowired
    FaturaRepository repositoryFatura;
    @Autowired
    SituacaoRepository repositorySituacao;

    public CartaoCredito cadastrarCartao(CartaoCredito novoCartao) {
        repositoryCartao.save(novoCartao);
        Situacao s = repositorySituacao.findById(1).get();
        Fatura f = new Fatura();
        f.setValor(0.0);
        f.setDataReferencia(LocalDate.now());
        f.setFkCartao(novoCartao);
        f.setFkSituacao(s);
        repositoryFatura.save(f);

        return novoCartao;
    }

    public List<CartaoCredito> listarCartoes(int idUsuario) {
        return repositoryCartao.listarCartaoCredito(idUsuario);
    }

    public List<CartaoCredito> listarCartoesConta(int idUsuario) {
        return repositoryCartao.listarCartaoCreditoConta(idUsuario);
    }

    public List<Transacao> getTransacaoFatura(int idCartao, int mes) {
        return repositoryCartao.buscarFaturaPorCartao(idCartao, mes);
    }
}
