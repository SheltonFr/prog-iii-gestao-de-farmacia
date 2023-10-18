package view.fornecedor.table_models;

import controllers.FornecedorController;

import javax.swing.table.AbstractTableModel;

public class MainModel extends AbstractTableModel {

    private FornecedorController controller;
    private String[] columns = {"Numero", "Nome", "Nuit"};

    public MainModel(FornecedorController controller) {
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
            return controller.getByIndex(rowIndex).getNumero();
        } else if(columnIndex == 1){
            return controller.getByIndex(rowIndex).getNome();
        } else if(columnIndex == 2){
            return controller.getByIndex(rowIndex).getNuit();
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
