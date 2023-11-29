package sptech.safemoney.dto.req;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import sptech.safemoney.dominio.*;

import java.time.LocalDate;

public class DespesaCreditoDTO {
    private String nome;
    private LocalDate data;
    private Double valor;
    private int parcelas;
    private int parcelaAtual;
    private Categoria categoria;
    private TipoTransacao tipo;
    private CartaoCredito cartao;
    private Fatura fatura;
    private double saldoAnterior;

    public DespesaCreditoDTO(String nome, LocalDate data, Double valor, int parcelas, int parcelaAtual, Categoria categoria, TipoTransacao tipo, CartaoCredito cartao, Fatura fatura, double saldoAnterior) {
        this.nome = nome;
        this.data = data;
        this.valor = valor;
        this.parcelas = parcelas;
        this.parcelaAtual = parcelaAtual;
        this.categoria = categoria;
        this.tipo = tipo;
        this.cartao = cartao;
        this.fatura = fatura;
        this.saldoAnterior = saldoAnterior;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public int getParcelaAtual() {
        return parcelaAtual;
    }

    public void setParcelaAtual(int parcelaAtual) {
        this.parcelaAtual = parcelaAtual;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public CartaoCredito getCartao() {
        return cartao;
    }

    public void setCartao(CartaoCredito cartao) {
        this.cartao = cartao;
    }

    public Fatura getFatura() {
        return fatura;
    }

    public void setFatura(Fatura fatura) {
        this.fatura = fatura;
    }

    public double getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(double saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }
}
