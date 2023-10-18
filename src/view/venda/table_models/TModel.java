package view.venda.table_models;

import controllers.ProdutoController;
import controllers.VendaController;
import controllers.VendaProdutoController;
import model.Produto;
import model.VendaProdutos;

import javax.swing.table.AbstractTableModel;

public class TModel extends AbstractTableModel {

    private VendaController vendaController = new VendaController();
    private ProdutoController produtoController = new ProdutoController();
    private VendaProdutoController controller;

    private final String[] columns = {"Cod. Prod.", "Nome Produto", "Quanridade", "Valor Un.", "Valor total"};

    public TModel(VendaProdutoController controller) {
        this.controller = controller;
    }

    public TModel() {

    }

    @Override
    public int getRowCount() {
        return controller.getTem().size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VendaProdutos v = controller.getTem().get(rowIndex);
        int prodCod = v.getProduto();
        Produto produto = produtoController.getById(prodCod);
        if(columnIndex == 0){
            return produto.getCodigo();
        } else if (columnIndex == 1) {
            return produto.getNome();
        } else if (columnIndex == 2) {
            return v.getQuantidade();
        } else if (columnIndex == 3) {
            return produto.getPreco();
        } else if (columnIndex == 4) {
            return v.getTotal();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
