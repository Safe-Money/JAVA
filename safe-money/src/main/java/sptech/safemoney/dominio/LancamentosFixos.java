package sptech.safemoney.dominio;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class LancamentosFixos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private double valor;
    private LocalDate data;
    @ManyToOne
    private ContaEntity fkConta;
    @ManyToOne
    private TipoTransacao fkTipoTransacao;
    @ManyToOne
    private Categoria fkCategoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public ContaEntity getFkConta() {
        return fkConta;
    }

    public void setFkConta(ContaEntity fkConta) {
        this.fkConta = fkConta;
    }

    public TipoTransacao getFkTipoTransacao() {
        return fkTipoTransacao;
    }

    public void setFkTipoTransacao(TipoTransacao fkTipoTransacao) {
        this.fkTipoTransacao = fkTipoTransacao;
    }

    public Categoria getFkCategoria() {
        return fkCategoria;
    }

    public void setFkCategoria(Categoria fkCategoria) {
        this.fkCategoria = fkCategoria;
    }
}
