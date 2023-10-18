package view.venda.table_models;

import controllers.ClienteController;
import controllers.VendaController;
import model.Cliente;
import model.Venda;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ListaModel extends AbstractTableModel {


    final String[] columns = {"Codigo", "Nome Cliente", "Data"};
    private VendaController controller = new VendaController();
    private ClienteController clienteController = new ClienteController();

    @Override
    public int getRowCount() {
        return controller.getLista().size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venda venda = controller.getLista().get(rowIndex);
        Cliente cliente = clienteController.getById(venda.getCliente());


        if(columnIndex == 0){
            return venda.getId();
        } else if (columnIndex == 1) {
            return cliente.getNome();
        } else if (columnIndex == 2) {
            return venda.getDataVenda();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
