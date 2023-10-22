package sptech.safemoney.dominio;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

@Entity
@Table(name = "transacao")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String nome;
    @Past
    private LocalDate data;
    @PositiveOrZero
    private Double valor;
    @PositiveOrZero
    private Integer tipo;
    @ManyToOne
    private CartaoCredito cartaoCredito;
    @ManyToOne
    private Conta conta;

    @ManyToOne
    private Categoria categoria;


    public Transacao(int id, String nome, LocalDate data, Double valor, Integer tipo, CartaoCredito cartaoCredito, Conta conta, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
        this.cartaoCredito = cartaoCredito;
        this.conta = conta;
        this.categoria = categoria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Transacao() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(CartaoCredito cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
