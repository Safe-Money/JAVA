package sptech.safemoney.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "conta")
public class ContaEntity {
    private String nome;
    private String banco;
    private int tipo;
}
