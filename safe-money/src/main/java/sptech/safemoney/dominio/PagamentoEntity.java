package sptech.safemoney.dominio;

import jakarta.persistence.*;

@Entity
@Table(name = "pagamento")
public class PagamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Long idPix;
    @ManyToOne
    private UsuarioEntity fkUsuario;

    public PagamentoEntity() {
    }

    public PagamentoEntity(Long idPix, UsuarioEntity fkUsuario) {
        this.idPix = idPix;
        this.fkUsuario = fkUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getIdPix() {
        return idPix;
    }

    public void setIdPix(Long idPix) {
        this.idPix = idPix;
    }

    public UsuarioEntity getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(UsuarioEntity fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
