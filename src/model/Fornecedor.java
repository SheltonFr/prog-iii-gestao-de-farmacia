package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Fornecedor  implements Serializable {
    private Integer numero;
    private String nome;
    private String nuit;
    private LocalDateTime dataCadastro;

    public Fornecedor() {

    }

    public Fornecedor(Integer numero, String nome, String nuit, LocalDateTime dataCadastro) {
        this.numero = numero;
        this.nome = nome;
        this.nuit = nuit;
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

    public String getNuit() {
        return nuit;
    }

    public void setNuit(String nuit) {
        this.nuit = nuit;
    }


}
