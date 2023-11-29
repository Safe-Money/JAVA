package sptech.safemoney.dto.res;

import java.time.LocalDate;

public class CartaoFaturaDTO {
    private String bandeira;
    private String nome;
    private String bancoVinculado;
    private LocalDate vencimento;
    private double faturaValor;
    private boolean faturaAtrasada;

    public CartaoFaturaDTO(String bandeira, String nome, String bancoVinculado, LocalDate vencimento, double faturaValor, boolean faturaAtrasada) {
        this.bandeira = bandeira;
        this.nome = nome;
        this.bancoVinculado = bancoVinculado;
        this.vencimento = vencimento;
        this.faturaValor = faturaValor;
        this.faturaAtrasada = faturaAtrasada;
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
}
