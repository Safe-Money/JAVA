package sptech.safemoney.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sptech.safemoney.dominio.LancamentosFixos;
import sptech.safemoney.repositorio.LancamentosFixosRepository;
import sptech.safemoney.utils.FilaObj;
import sptech.safemoney.utils.PilhaObj;

import java.util.List;

@Service
public class LancamentosFixosService {
    private FilaObj<LancamentosFixos> filaCadastro = new FilaObj<>(10);
    private PilhaObj<LancamentosFixos> pilha = new PilhaObj<>(10);;

    @Autowired
    LancamentosFixosRepository lancamentosFixosRepository;
    public List<LancamentosFixos> buscarLancamentosFixos(int idConta) {
        return lancamentosFixosRepository.getLancamentosFixosPorConta(idConta);
    }

    public void cadastrarLancamento(LancamentosFixos novoL) {
        filaCadastro.insert(novoL);

        pilha.push(novoL);

        lancamentosFixosRepository.save(filaCadastro.poll());
    }

    public void desfazerLanc() {
        if (pilha.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sem lançamentos para desfazer");
        }

        LancamentosFixos lanc = pilha.pop();
        lancamentosFixosRepository.delete(lanc);
    }
}
