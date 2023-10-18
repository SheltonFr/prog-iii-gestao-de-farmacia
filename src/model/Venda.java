package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Venda implements Serializable {

    private Integer id;
    private int cliente;
    private LocalDate dataVenda;
    private Double valorBruto;
    private Double valorLiquido;
    private Double desconto;

    public ArrayList<VendaProdutos> vendaProdutos;

    public Venda(Integer id, Integer cliente, LocalDate dataVenda, Double valorBruto, Double valorLiquido, Double desconto) {
        this.id = id;
        this.cliente = cliente;
        this.dataVenda = dataVenda;
        this.valorBruto = valorBruto;
        this.valorLiquido = valorLiquido;
        this.desconto = desconto;
    }

    public Venda(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Double getValorBruto() {
        return valorBruto;
    }

    public void setValorBruto(Double valorBruto) {
        this.valorBruto = valorBruto;
    }

    public Double getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(Double valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }
}
