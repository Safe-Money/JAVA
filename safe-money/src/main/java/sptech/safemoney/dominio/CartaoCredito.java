package sptech.safemoney.dominio;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

@Entity
@Table(name = "cartaoCredito")
public class CartaoCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String bandeira;
    @PositiveOrZero
    private Double limite;
    @Future
    private LocalDate dataFechamento;
    @Past
    private LocalDate dataLancamento;

    @ManyToOne
    private Conta conta;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public CartaoCredito(int id, String bandeira, Double limite, LocalDate dataFechamento, LocalDate dataLancamento, Conta conta) {
        this.id = id;
        this.bandeira = bandeira;
        this.limite = limite;
        this.dataFechamento = dataFechamento;
        this.dataLancamento = dataLancamento;
        this.conta = conta;
    }

    public CartaoCredito() {
    }
}
