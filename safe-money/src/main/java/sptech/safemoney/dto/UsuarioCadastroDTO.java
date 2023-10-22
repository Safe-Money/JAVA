package sptech.safemoney.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sptech.safemoney.dominio.UsuarioEntity;
import java.time.LocalDate;

public class UsuarioCadastroDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
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
