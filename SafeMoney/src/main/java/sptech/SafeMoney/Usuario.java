//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package sptech.SafeMoney;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.concurrent.ThreadLocalRandom;
public class Usuario {

    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String dtNascimento;

    public Usuario(String nome, String email, String senha, String dtNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
    }

    public Usuario() {
        this.id = ThreadLocalRandom.current().nextInt(1, 1001);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDtNascimento() {
        return this.dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }
}
