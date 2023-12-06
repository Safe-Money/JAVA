package sptech.safemoney.servico;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sptech.safemoney.dominio.Objetivo;
import sptech.safemoney.dominio.Transacao;
import sptech.safemoney.dto.res.GastoPorDiaDTO;
import sptech.safemoney.repositorio.*;
import sptech.safemoney.utils.ListaObj;
import sptech.safemoney.utils.OrdenacaoPesquisa;
import sptech.safemoney.utils.PilhaObj;

import java.util.ArrayList;
import java.util.List;


@Service
public class ObjetivoService {
    @Autowired
    private ObjetivoRepository repository;



    public void depositar(Objetivo obj, Double valorInvestido) {
        Double valorAposDeposito = valorInvestido+obj.getValorInvestido();
        if (valorAposDeposito>obj.getValorFinal()) {
            System.out.println("ENTREI NO ERRO");



            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O valor após o depósito não pode ser maior que o valor investido");
        }
        else {

            obj.setValorInvestido(obj.getValorInvestido()+valorInvestido);

            System.out.println("Comparando valores: "+obj.getValorInvestido()+ " "+ obj.getValorFinal());
            if (obj.getValorInvestido().equals(obj.getValorFinal())){
                obj.setConcluida(1);
            }
            System.out.println("CHEGUEI NO SAVE");

            repository.save(obj);
        }
    }

    public void concluir(Objetivo obj, Double valorInvestido) {
        Double valorAposDeposito = valorInvestido+obj.getValorInvestido();
        if (valorAposDeposito>obj.getValorFinal()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O valor após o depósito não pode ser maior que o valor investido");
        }
        else {
            obj.setValorInvestido(obj.getValorInvestido()+valorInvestido);
            repository.save(obj);
        }
    }

    public void verificaSave(Objetivo obj) {

        if (obj.getDataInicio().isAfter(obj.getDataTermino())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data de início não pode ser depois da de termino");
        }
        else {
            repository.save(obj);
        }
    }

    public void verificaValores(Objetivo obj) {


            repository.save(obj);

    }



}
