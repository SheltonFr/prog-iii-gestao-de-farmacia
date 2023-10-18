package view.filtro.table_models;

import controllers.ArmazemController;
import controllers.ProdutoController;
import model.Produto;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;

public class ValidadeModel extends AbstractTableModel {

    private ProdutoController produtoController;
    private ArmazemController armazemController;
    private ArrayList<Produto> abaixoValidade;

    private String[] columns = {"Numero", "Quantidade","Validade", "Armazem", "Nome"};

    public ValidadeModel(){
        produtoController = new ProdutoController();
        armazemController = new ArmazemController();
        getProdutoOverValidade();
    }

    private void getProdutoOverValidade(){
        abaixoValidade = new ArrayList<>();
        ArrayList<Produto> lista = produtoController.getLista();

        for (Produto prod:
                lista) {
            if((prod.getValidade().isBefore(LocalDate.now().plusWeeks(1)))){
                abaixoValidade.add(prod);
            }
        }
    }
    @Override
    public int getRowCount() {
        return abaixoValidade.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return abaixoValidade.get(rowIndex).getCodigo();
        } else if (columnIndex == 1) {
            return abaixoValidade.get(rowIndex).getQuantidade();
        } else if (columnIndex == 2) {
            return abaixoValidade.get(rowIndex).getValidade().toString();
        } else if (columnIndex == 3) {
            return armazemController.getLista().get(abaixoValidade.get(rowIndex).getArmazem()).getNumero();
        } else if (columnIndex == 4) {
            return abaixoValidade.get(rowIndex).getNome();
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
