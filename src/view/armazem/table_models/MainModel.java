package view.armazem.table_models;

import controllers.ArmazemController;

import javax.swing.table.AbstractTableModel;

public class MainModel extends AbstractTableModel {

    private ArmazemController controller;
    private String[] columns = {"Numero", "Nome"};

    public MainModel(ArmazemController controller) {
        this.controller = controller;
    }

    @Override
    public int getRowCount() {
        return controller.getSize();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return controller.getByIndex(rowIndex).getNumero();
        } else if(columnIndex == 1){
            return controller.getByIndex(rowIndex).getNome();
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
