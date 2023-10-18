package view.produto.table_models;

import controllers.ArmazemController;
import controllers.FornecedorController;
import model.Produto;

import javax.swing.table.AbstractTableModel;

public class BuscaModel extends AbstractTableModel {

    Produto produto;

    private ArmazemController armazemController = new ArmazemController();
    private FornecedorController fornecedorController = new FornecedorController();
    private String[] columns =  {"Numero", "Armazem", "Stock minimo", "Quantidade", "Nome", "Fornecedor", "Validade"};

    public BuscaModel(Produto produto){
        this.produto = produto;
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return produto.getCodigo();
        } else if(columnIndex == 1){
            return armazemController.getByIndex(produto.getArmazem()).getNome();
        } else if(columnIndex == 2){
            return produto.getMinStock();
        } else if(columnIndex == 3) {
            return produto.getQuantidade();
        } else if(columnIndex == 4){
            return produto.getNome();
        } else if (columnIndex == 5) {
            return fornecedorController.getByIndex(produto.getFornecedor()).getNome();
        } else if (columnIndex == 6){
            return produto.getValidade().toString();
        }

        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public String getColumnName(int index){
        return columns[index];
    }
}
