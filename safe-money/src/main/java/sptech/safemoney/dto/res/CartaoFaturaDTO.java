package sptech.safemoney.dto.res;

import java.time.LocalDate;

public class CartaoFaturaDTO {
    private int id;
    private String bandeira;
    private String nome;
    private String bancoVinculado;
    private LocalDate vencimento;
    private LocalDate fechamento;
    private double faturaValor;
    private boolean faturaAtrasada;
    private double limite;

    public CartaoFaturaDTO(int id, String bandeira, String nome, String bancoVinculado, LocalDate vencimento, LocalDate fechamento, double faturaValor, boolean faturaAtrasada, double limite) {
        this.id = id;
        this.bandeira = bandeira;
        this.nome = nome;
        this.bancoVinculado = bancoVinculado;
        this.vencimento = vencimento;
        this.fechamento = fechamento;
        this.faturaValor = faturaValor;
        this.faturaAtrasada = faturaAtrasada;
        this.limite = limite;
    }

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBancoVinculado() {
        return bancoVinculado;
    }

    public void setBancoVinculado(String bancoVinculado) {
        this.bancoVinculado = bancoVinculado;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public LocalDate getFechamento() {
        return fechamento;
    }

    public void setFechamento(LocalDate fechamento) {
        this.fechamento = fechamento;
    }

    public double getFaturaValor() {
        return faturaValor;
    }

    public void setFaturaValor(double faturaValor) {
        this.faturaValor = faturaValor;
    }

    public boolean isFaturaAtrasada() {
        return faturaAtrasada;
    }

    public void setFaturaAtrasada(boolean faturaAtrasada) {
        this.faturaAtrasada = faturaAtrasada;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
}
