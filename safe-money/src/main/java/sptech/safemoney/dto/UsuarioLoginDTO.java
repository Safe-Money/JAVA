package sptech.safemoney.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class UsuarioLoginDTO {
    @NotBlank
    @Schema(description = "E-mail do usuário", example = "braian.melhorprofessor@sptech.school")
    private String email;

    @NotBlank
    @Schema(description = "Senha do usuário", example = "monteiroTambem")
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
