package sptech.safemoney.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.safemoney.dominio.LancamentosFixos;
import sptech.safemoney.repositorio.LancamentosFixosRepository;

import java.util.List;

@Service
public class LancamentosFixosService {

    @Autowired
    LancamentosFixosRepository lancamentosFixosRepository;
    public List<LancamentosFixos> buscarLancamentosFixos(int idConta) {
        return lancamentosFixosRepository.getLancamentosFixosPorConta(idConta);
    }

    public void cadastrarLancamento(LancamentosFixos novoL) {
        lancamentosFixosRepository.save(novoL);
    }
}
