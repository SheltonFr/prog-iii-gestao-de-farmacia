package view.cliente.table_models;

import model.Cliente;

import javax.swing.table.AbstractTableModel;

public class BuscaModel extends AbstractTableModel {

    Cliente cliente;
    private String[] columns = {"Numero", "Nome", "Bi", "Data de Cadastro"};

    public BuscaModel(Cliente cliente){
        this.cliente = cliente;
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
            return this.cliente.getId();
        } else if(columnIndex == 1){
            return this.cliente.getNome();
        } else if(columnIndex ==2){
            return this.cliente.getBi();
        } else if (columnIndex == 3) {
            return this.cliente.getDataCadastro().toString();
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
