package sptech.safemoney.dto.req;

import sptech.safemoney.dominio.Categoria;
import sptech.safemoney.dominio.ContaEntity;
import sptech.safemoney.dominio.TipoTransacao;

import java.time.LocalDate;

public class DespesaDTO {
    private String nome;
    private LocalDate data;
    private double valor;
    private ContaEntity conta;
    private Categoria categoria;
    private TipoTransacao tipo;
    private double saldoAnterior;

    public DespesaDTO(String nome, LocalDate data, double valor, ContaEntity conta, Categoria categoria, TipoTransacao tipo, double saldoAnterior) {
        this.nome = nome;
        this.data = data;
        this.valor = valor;
        this.conta = conta;
        this.categoria = categoria;
        this.tipo = tipo;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public ContaEntity getConta() {
        return conta;
    }

    public void setConta(ContaEntity conta) {
        this.conta = conta;
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

    public double getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(double saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }
}
