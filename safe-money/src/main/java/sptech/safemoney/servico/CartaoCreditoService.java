package sptech.safemoney.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import sptech.safemoney.dominio.CartaoCredito;
import sptech.safemoney.dominio.Fatura;
import sptech.safemoney.dominio.Situacao;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.dto.res.CartaoFaturaDTO;
import sptech.safemoney.repositorio.CartaoCreditoRepository;
import sptech.safemoney.repositorio.FaturaRepository;
import sptech.safemoney.repositorio.SituacaoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
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

        for (int i = 1; i <= 12; i++) {
            Fatura f = new Fatura();
            f.setValor(0.0);

            if (LocalDate.now().isAfter(novoCartao.getDataFechamento())) {
                f.setDataReferencia(novoCartao.getDataFechamento().plusMonths(i));
            } else {
                f.setDataReferencia(novoCartao.getDataFechamento().plusMonths(i - 1));
            }

            f.setFkCartao(novoCartao);
            f.setFkSituacao(s);
            repositoryFatura.save(f);
        }

        return novoCartao;
    }
    public List<CartaoFaturaDTO> listarCartoes(int idUsuario) {
        List<CartaoCredito> cartoes = repositoryCartao.listarCartaoCredito(idUsuario);
        List<CartaoFaturaDTO> cartoesDto = new ArrayList<>();

        double valorFatura = 0;

        for (CartaoCredito c : cartoes) {
            LocalDate dataAtual = LocalDate.now();
            LocalDate dataFechamento = c.getDataFechamento();
            LocalDate dataProximoFechamento = dataFechamento.plusMonths(1);

            // List<Fatura> faturas = repositoryFatura.buscarFaturaPorCartao(c.getId());
            List<Fatura> faturas = repositoryFatura.findByFkCartaoId(c.getId());

            boolean faturaEncontrada = false;
            boolean faturaAtrasada = false;

            for (Fatura f : faturas) {
                if (f.getFkSituacao().getId() == 1 && faturaEncontrada == false) {
                    faturaEncontrada = true;
                    valorFatura = f.getValor();
                    if (LocalDate.now().isAfter(f.getFkCartao().getDataVencimento())) {
                        faturaAtrasada = true;
                    }
                }
            }

            if (!faturaEncontrada) {
                valorFatura = 0.0;
            }


            /*

            if (dataAtual.equals(dataFechamento) || dataAtual.isAfter(dataFechamento) && dataAtual.isBefore(dataProximoFechamento)) {
                valorFatura = repositoryFatura.buscarFaturaValor(c.getId(), dataAtual);
            } else {
                valorFatura = repositoryFatura.buscarFaturaValor(c.getId(), dataAtual.plusMonths(1));
            }

            */

            CartaoFaturaDTO cartao = new CartaoFaturaDTO(c.getBandeira(), c.getNome(), c.getConta().getBanco(), c.getDataVencimento(), valorFatura, faturaAtrasada, c.getLimite());
            cartoesDto.add(cartao);
        }

        return cartoesDto;
    }

    public List<CartaoFaturaDTO> listarCartoesConta(int idConta) {
        List<CartaoCredito> cartoes = repositoryCartao.listarCartaoCreditoConta(idConta);

        List<CartaoFaturaDTO> cartoesDto = new ArrayList<>();

        double valorFatura = 0;

        for (CartaoCredito c : cartoes) {
            LocalDate dataAtual = LocalDate.now();
            LocalDate dataFechamento = c.getDataFechamento();
            LocalDate dataProximoFechamento = dataFechamento.plusMonths(1);

            // List<Fatura> faturas = repositoryFatura.buscarFaturaPorCartao(c.getId());
            List<Fatura> faturas = repositoryFatura.findByFkCartaoId(c.getId());

            boolean faturaEncontrada = false;
            boolean faturaAtrasada = false;

            for (Fatura f : faturas) {
                if (f.getFkSituacao().getId() == 1 && faturaEncontrada == false) {
                    faturaEncontrada = true;
                    valorFatura = f.getValor();
                    if (LocalDate.now().isAfter(f.getFkCartao().getDataVencimento())) {
                        faturaAtrasada = true;
                    }
                }
            }

            if (!faturaEncontrada) {
                valorFatura = 0.0;
            }


            /*

            if (dataAtual.equals(dataFechamento) || dataAtual.isAfter(dataFechamento) && dataAtual.isBefore(dataProximoFechamento)) {
                valorFatura = repositoryFatura.buscarFaturaValor(c.getId(), dataAtual);
            } else {
                valorFatura = repositoryFatura.buscarFaturaValor(c.getId(), dataAtual.plusMonths(1));
            }

            */

            CartaoFaturaDTO cartao = new CartaoFaturaDTO(c.getBandeira(), c.getNome(), c.getConta().getBanco(), c.getDataVencimento(), valorFatura, faturaAtrasada, c.getLimite());
            cartoesDto.add(cartao);
        }

        return cartoesDto;
    }

    public List<Transacao> getTransacaoFatura(int idCartao, int mes) {
        return repositoryCartao.buscarFaturaPorCartao(idCartao, mes);
    }
}
