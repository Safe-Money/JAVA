package sptech.safemoney.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.safemoney.dominio.CartaoCredito;
import sptech.safemoney.repositorio.CartaoCreditoRepository;

import java.util.List;

@Service
public class CartaoCreditoService {
    @Autowired
    CartaoCreditoRepository repositoryCartao;

    public List<CartaoCredito> listarCartoes(int idUsuario) {
        return repositoryCartao.listarCartaoCredito(idUsuario);
    }

    public List<CartaoCredito> listarCartoesConta(int idUsuario) {
        return repositoryCartao.listarCartaoCreditoConta(idUsuario);
    }
}
