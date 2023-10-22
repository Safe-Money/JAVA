package sptech.safemoney.dto;

import jakarta.validation.constraints.NotBlank;

public class UsuarioLoginDTO {
    @NotBlank
    private String email;

    @NotBlank
    // @Size(min = 8, max = 8)
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
