package sptech.safemoney.dominio;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String nome;
    @NotBlank
    private String imagemAssociada;


    public Categoria(int id, String nome, String imagemAssociada) {
        this.id = id;
        this.nome = nome;
        this.imagemAssociada = imagemAssociada;
    }

    public Categoria() {
    }

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

    public String getImagemAssociada() {
        return imagemAssociada;
    }

    public void setImagemAssociada(String imagemAssociada) {
        this.imagemAssociada = imagemAssociada;
    }
}
