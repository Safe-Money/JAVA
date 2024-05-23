package sptech.safemoney.dto.req;

import sptech.safemoney.dominio.UsuarioEntity;

import java.time.LocalDateTime;

public class PixDTO {
    private String firstName;
    private String email;
    private String cpf;

    public PixDTO(String firstName, String email, String cpf) {
        this.firstName = firstName;
        this.email = email;
        this.cpf = cpf;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
