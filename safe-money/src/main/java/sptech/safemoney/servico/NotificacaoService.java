package sptech.safemoney.servico;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sptech.safemoney.dominio.CartaoCredito;
import sptech.safemoney.dominio.Fatura;
import sptech.safemoney.dto.req.CartaoFaturaDTO;
import sptech.safemoney.dto.res.NotificacaoDTO;
import sptech.safemoney.repositorio.CartaoCreditoRepository;
import sptech.safemoney.repositorio.FaturaRepository;
import sptech.safemoney.repositorio.PlanejamentoRepository;
import sptech.safemoney.repositorio.TransacaoRepository;
import sptech.safemoney.utils.FilaObj;

import java.sql.Array;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificacaoService {
    @Autowired
    TransacaoRepository transacaoRepository;
    @Autowired
    PlanejamentoRepository planejamentoRepository;
    @Autowired
    FaturaRepository faturaRepository;
    @Autowired
    CartaoCreditoRepository cartaoRepository;
    private FilaObj<NotificacaoDTO> fila;

    @PostConstruct
    public void executarFila() {
        this.fila = new FilaObj<>(50);
    }

    public void verificarFechamento(int idUsuario) {
        List<CartaoCredito> cartoes = cartaoRepository.listarCartaoCredito(idUsuario);
        LocalDate diaHoje = LocalDate.now();

        List<NotificacaoDTO> dia1 = new ArrayList<>();
        List<NotificacaoDTO> dia2 = new ArrayList<>();
        List<NotificacaoDTO> dia3 = new ArrayList<>();
        List<NotificacaoDTO> dia4 = new ArrayList<>();
        List<NotificacaoDTO> dia5 = new ArrayList<>();

        for (CartaoCredito c : cartoes) {
            LocalDate dataFechamento = c.getDataFechamento();
            long diferencaDias = ChronoUnit.DAYS.between(diaHoje, dataFechamento);

            if (diferencaDias == 5) {
                NotificacaoDTO n = new NotificacaoDTO("Falta %d dias para a fatura do cartao %s fechar".formatted(diferencaDias, c.getNome()), diaHoje);
                dia5.add(n);
            } else if (diferencaDias == 4){
                NotificacaoDTO n = new NotificacaoDTO("Falta %d dias para a fatura do cartao %s fechar".formatted(diferencaDias, c.getNome()), diaHoje);
                dia4.add(n);
            } else if (diferencaDias == 3){
                NotificacaoDTO n = new NotificacaoDTO("Falta %d dias para a fatura do cartao %s fechar".formatted(diferencaDias, c.getNome()), diaHoje);
                dia3.add(n);;
            } else if (diferencaDias == 2){
                NotificacaoDTO n = new NotificacaoDTO("Falta %d dias para a fatura do cartao %s fechar".formatted(diferencaDias, c.getNome()), diaHoje);
                dia2.add(n);
            } else if (diferencaDias == 1){
                NotificacaoDTO n = new NotificacaoDTO("Falta %d dias para a fatura do cartao %s fechar".formatted(diferencaDias, c.getNome()), diaHoje);
                dia1.add(n);
            }
        }

        for (int i = 0; i < dia1.size(); i++) {
            fila.insert(dia1.get(i));
        }

        for (int i = 0; i < dia2.size(); i++) {
            fila.insert(dia2.get(i));
        }

        for (int i = 0; i < dia3.size(); i++) {
            fila.insert(dia3.get(i));
        }

        for (int i = 0; i < dia4.size(); i++) {
            fila.insert(dia4.get(i));
        }

        for (int i = 0; i < dia5.size(); i++) {
            fila.insert(dia5.get(i));
        }

    }

    public List<NotificacaoDTO> getNotificacao(int idUsuario) {
        List<NotificacaoDTO> notificacoes = new ArrayList<>();
        while (!fila.isEmpty()) {
            notificacoes.add(fila.poll());
        }

        return notificacoes;
    }

}
