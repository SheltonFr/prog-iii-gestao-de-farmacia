package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Cliente implements Serializable {

    private Integer id;
    private String nome;
    private String bi;
    private LocalDate dataCadastro;

    public Cliente(Integer id, String nome, String bi, LocalDate dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.bi = bi;
        this.dataCadastro = dataCadastro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBi() {
        return bi;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", bi='" + bi + '\'' +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}