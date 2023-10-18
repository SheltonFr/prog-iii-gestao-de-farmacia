package view.cliente;

import controllers.ClienteController;

import javax.swing.*;

public class RegistroCliente extends JFrame {

    private ClienteController controller;
    private JTabbedPane tabbedPane;
    private RegistroPanel registro;
    private BuscaPanel busca;

    public RegistroCliente(){
        initComponent();
        this.setVisible(true);
    }

    public void initComponent(){

        controller = new ClienteController();

        this.setTitle("Clientes");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(900,570);
        this.setResizable(false);
        this.setLocationRelativeTo(null);


        tabbedPane = new JTabbedPane();
        registro = new RegistroPanel(controller);
        busca = new BuscaPanel(controller, tabbedPane, registro);





        tabbedPane.addTab("Registro", registro);
        tabbedPane.addTab("Pesquisa", busca);
        this.add(tabbedPane);
    }

    public static void main(String[] args) {
        new RegistroCliente();
    }
}
