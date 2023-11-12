package sptech.safemoney.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sptech.safemoney.dominio.ContaEntity;
import sptech.safemoney.repositorio.ContaRepository;

import java.util.List;

@Service
public class ContaService {
    @Autowired
    ContaRepository repositoryConta;

    public List<ContaEntity> buscarContas(int idUsuario) {
        return repositoryConta.listarContas(idUsuario);


    }
}
