package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Administrador implements Serializable {
    private String nome;
    private String username;
    private String password;
    private LocalDateTime dataCadastro;

    public Administrador() {

    }

    public Administrador(String nome, String username, String password, LocalDateTime dataCadastro) {
        this.nome = nome;
        this.username = username;
        this.password = password;
        this.dataCadastro = dataCadastro;
    }

    public Administrador(String nome, String username, String password) {
        this.nome = nome;
        this.username = username;
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "nome='" + nome + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}
