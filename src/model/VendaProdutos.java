package model;

import java.io.Serializable;

public class VendaProdutos implements Serializable {

    private Integer id;
    private Integer produto;
    private Integer vendas;
    private Double precoUnitario;
    private Integer quantidade;
    private Double total;


    public VendaProdutos(Integer id, Integer produto, Integer vendas, Double precoUnitario, Integer quantidade) {
        this.id = id;
        this.produto = produto;
        this.vendas = vendas;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;

        this.total = quantidade * precoUnitario;

    }

    public VendaProdutos(Integer produto, Integer vendas, Double precoUnitario, Integer quantidade) {
        this.produto = produto;
        this.vendas = vendas;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;

        this.total = quantidade * precoUnitario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduto() {
        return produto;
    }

    public void setProduto(Integer produto) {
        this.produto = produto;
    }

    public Integer getVendas() {
        return vendas;
    }

    public void setVendas(Integer vendas) {
        this.vendas = vendas;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
