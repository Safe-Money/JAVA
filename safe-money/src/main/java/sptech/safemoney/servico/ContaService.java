package sptech.safemoney.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sptech.safemoney.dominio.ContaEntity;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.dto.res.InfoContaDTO;
import sptech.safemoney.repositorio.ContaRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContaService {
    @Autowired
    ContaRepository repositoryConta;

    public List<ContaEntity> buscarContas(int idUsuario) {
        return repositoryConta.listarContas(idUsuario);
    }

    public InfoContaDTO getInfoConta(int id) {
        Double despesaMes = repositoryConta.getDespesaContaMes(id, LocalDate.now());
        if (despesaMes == null) {
            despesaMes = 0.0;
        }

        Double receitaMes = repositoryConta.getReceitaContaMes(id, LocalDate.now());
        if (receitaMes == null) {
            receitaMes = 0.0;
        }

        return new InfoContaDTO(despesaMes, receitaMes);
    }
}
