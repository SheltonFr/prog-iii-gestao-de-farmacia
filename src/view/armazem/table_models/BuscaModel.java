package view.armazem.table_models;

import model.Armazem;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class BuscaModel extends AbstractTableModel {

    Armazem armazem;
    private String[] columns = {"Numero", "Nome"};

    public BuscaModel(Armazem armazem){
        this.armazem = armazem;
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return this.armazem.getNumero();
        } else if(columnIndex == 1){
            return this.armazem.getNome();
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
