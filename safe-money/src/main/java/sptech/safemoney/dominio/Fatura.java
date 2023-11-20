package sptech.safemoney.dominio;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Fatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double valor;
    private LocalDate dataReferencia;
    @ManyToOne
    private CartaoCredito fkCartao;
    @ManyToOne
    private Situacao fkSituacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(LocalDate dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public CartaoCredito getFkCartao() {
        return fkCartao;
    }

    public void setFkCartao(CartaoCredito fkCartao) {
        this.fkCartao = fkCartao;
    }

    public Situacao getFkSituacao() {
        return fkSituacao;
    }

    public void setFkSituacao(Situacao fkSituacao) {
        this.fkSituacao = fkSituacao;
    }
}
