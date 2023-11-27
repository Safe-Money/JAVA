package sptech.safemoney.dto.req;

import java.time.LocalDate;

public class CartaoFaturaDTO {
    private String nomeCartao;
    private String bandeira;
    private double limite;
    private LocalDate dataFechamento;
    private LocalDate dataVencimento;
    private LocalDate dataReferencia;
    private double valor;

    public CartaoFaturaDTO(String nomeCartao, String bandeira, double limite, LocalDate dataFechamento, LocalDate dataVencimento, LocalDate dataReferencia, double valor) {
        this.nomeCartao = nomeCartao;
        this.bandeira = bandeira;
        this.limite = limite;
        this.dataFechamento = dataFechamento;
        this.dataVencimento = dataVencimento;
        this.dataReferencia = dataReferencia;
        this.valor = valor;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(LocalDate dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
