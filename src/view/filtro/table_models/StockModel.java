package view.filtro.table_models;

import controllers.ArmazemController;
import controllers.ProdutoController;
import model.Produto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class StockModel extends AbstractTableModel {
    private ProdutoController produtoController;
    private ArmazemController armazemController;
    private ArrayList<Produto> abaixoStock;

    private String[] columns = {"Numero", "Stock Minimo", "Quantidade", "Armazem", "Nome"};

    public StockModel(){
        produtoController = new ProdutoController();
        armazemController = new ArmazemController();
        getProdutoOverMinStock();
    }

    private void getProdutoOverMinStock(){
        abaixoStock = new ArrayList<>();
        ArrayList<Produto> lista = produtoController.getLista();

        for (Produto prod:
             lista) {
            if(prod.getQuantidade() <= prod.getMinStock()){
                abaixoStock.add(prod);
            }
        }
    }
    @Override
    public int getRowCount() {
        return abaixoStock.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return abaixoStock.get(rowIndex).getCodigo();
        } else if (columnIndex == 1) {
            return abaixoStock.get(rowIndex).getMinStock();
        } else if (columnIndex == 2) {
            return abaixoStock.get(rowIndex).getQuantidade();
        } else if (columnIndex == 3) {
            return armazemController.getLista().get(rowIndex).getNumero();
        } else if (columnIndex == 4) {
            return abaixoStock.get(rowIndex).getNome();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
