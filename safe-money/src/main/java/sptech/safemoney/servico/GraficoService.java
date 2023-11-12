package sptech.safemoney.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.safemoney.repositorio.TransacaoRepository;

@Service
public class GraficoService {
    @Autowired
    private TransacaoRepository repositoryTransacao;

    public double receitaPrevista(int idUsuario){
        return repositoryTransacao.receitaPrevista(idUsuario);
    }
}
