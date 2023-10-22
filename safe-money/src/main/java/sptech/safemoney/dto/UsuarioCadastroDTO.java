package sptech.safemoney.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sptech.safemoney.dominio.UsuarioEntity;
import java.time.LocalDate;

public class UsuarioCadastroDTO {
    @NotBlank
    @Schema(description = "Nome do usuário", example = "Braian Hudson")
    private String nome;
    @NotBlank
    @Schema(description = "E-mail do usuário", example = "braian.melhorprofessor@sptech.school")
    private String email;
    @NotBlank
    @Schema(description = "Senha do usuário", example = "monteiroTambem")
    private String senha;
    @Schema(description = "Data de nascimento do usuário", example = "2002-05-08")
    private LocalDate dtNascimento;

    public UsuarioEntity convert(){
        this.senha = new BCryptPasswordEncoder().encode(getSenha());

        return new UsuarioEntity(nome, email, senha, dtNascimento);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }
}
