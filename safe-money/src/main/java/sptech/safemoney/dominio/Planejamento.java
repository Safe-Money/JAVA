package sptech.safemoney.dominio;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

@Entity
@Table(name = "planejamento")
public class Planejamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @PositiveOrZero
    private Double valorPlanejado;

    @Future
    private LocalDate data;

    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Categoria categoria;

    public Planejamento(Integer id, Double valorPlanejado, LocalDate data, Usuario usuario) {
        this.id = id;
        this.valorPlanejado = valorPlanejado;
        this.data = data;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValorPlanejado() {
        return valorPlanejado;
    }

    public void setValorPlanejado(Double valorPlanejado) {
        this.valorPlanejado = valorPlanejado;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
