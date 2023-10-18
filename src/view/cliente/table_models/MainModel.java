package view.cliente.table_models;

import controllers.ClienteController;

import javax.swing.table.AbstractTableModel;

public class MainModel extends AbstractTableModel {

    private ClienteController controller;
    private String[] columns = {"Numero", "Nome", "Bi", "Data de Cadastro"};

    public MainModel(ClienteController controller) {
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
            return controller.getByIndex(rowIndex).getId();
        } else if(columnIndex == 1){
            return controller.getByIndex(rowIndex).getNome();
        } else if(columnIndex == 2){
            return controller.getByIndex(rowIndex).getBi();
        } else if(columnIndex == 3){
            return controller.getByIndex(rowIndex).getDataCadastro().toString();
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
