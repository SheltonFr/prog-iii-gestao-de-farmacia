package view.produto.table_models;

import controllers.ArmazemController;
import controllers.FornecedorController;
import controllers.ProdutoController;

import javax.swing.table.AbstractTableModel;

public class MainModel extends AbstractTableModel {

    private ProdutoController controller;
    private ArmazemController armazemController = new ArmazemController();
    private FornecedorController fornecedorController = new FornecedorController();
    private String[] columns = {"Numero", "Armazem", "Stock minimo", "Quantidade", "Nome", "Fornecedor", "Validade"};

    public MainModel(ProdutoController controller) {
        this.controller = controller;
    }

    @Override
    public int getRowCount() {
        return controller.getSize();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return controller.getByIndex(rowIndex).getCodigo();
        } else if(columnIndex == 1){
            return armazemController.getByIndex(controller.getByIndex(rowIndex).getArmazem()).getNome();
        } else if(columnIndex == 2){
            return controller.getByIndex(rowIndex).getMinStock();
        } else if(columnIndex == 3) {
            return controller.getByIndex(rowIndex).getQuantidade();
        } else if(columnIndex == 4){
            return controller.getByIndex(rowIndex).getNome();
        } else if (columnIndex == 5) {
            return fornecedorController.getByIndex(controller.getByIndex(rowIndex).getFornecedor()).getNome();
        } else if (columnIndex == 6){
            return controller.getByIndex(rowIndex).getValidade().toString();
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
