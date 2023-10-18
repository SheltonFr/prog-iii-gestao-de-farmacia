package view.fornecedor.table_models;

import model.Fornecedor;

import javax.swing.table.AbstractTableModel;

public class BuscaModel extends AbstractTableModel {

    Fornecedor fornecedor;
    private String[] columns = {"Numero", "Nome", "Nuit"};

    public BuscaModel(Fornecedor fornecedor){
        this.fornecedor = fornecedor;
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
            return this.fornecedor.getNumero();
        } else if(columnIndex == 1){
            return this.fornecedor.getNome();
        } else if(columnIndex ==2){
            return this.fornecedor.getNuit();
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
