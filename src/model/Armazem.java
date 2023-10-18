package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Armazem implements Serializable {

    private Integer numero;
    private String nome;
    private LocalDateTime dataCadastro;
    private List<Produto> produtos;

    public Armazem(Integer numero, String nome, LocalDateTime dataCadastro) {
        this.numero = numero;
        this.nome = nome;
        this.dataCadastro = dataCadastro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
