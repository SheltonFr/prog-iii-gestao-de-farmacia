package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Produto implements Serializable {

    private Integer codigo;
    private Integer minStock;
    private Integer armazem;
    private Integer quantidade;
    private String nome;
    private LocalDateTime dactaCadastro;
    private LocalDate validade;
    private Integer fornecedor;
    private Double preco;

    public Produto() {

    }

    public Produto(Integer codigo, Integer minStock, Integer armazem, Integer quantidade,
                   String nome, Integer fornecedor, LocalDateTime dactaCadastro, LocalDate validade, Double preco) {
        this.codigo = codigo;
        this.minStock = minStock;
        this.armazem = armazem;
        this.quantidade = quantidade;
        this.nome = nome;
        this.fornecedor = fornecedor;
        this.dactaCadastro = dactaCadastro;
        this.validade = validade;
        this.preco = preco;
    }

    public LocalDateTime getDactaCadastro() {
        return dactaCadastro;
    }

    public void setDactaCadastro(LocalDateTime dactaCadastro) {
        this.dactaCadastro = dactaCadastro;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getMinStock() {
        return minStock;
    }

    public void setMinStock(Integer minStock) {
        this.minStock = minStock;
    }

    public Integer getArmazem() {
        return armazem;
    }

    public void setArmazem(Integer armazem) {
        this.armazem = armazem;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getFornecedor() {
        return fornecedor;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setFornecedor(Integer fornecedor) {
        this.fornecedor = fornecedor;
    }
}
