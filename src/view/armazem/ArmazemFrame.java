package view.armazem;

import controllers.ArmazemController;

import javax.swing.*;

public class ArmazemFrame extends JFrame {

    ArmazemController controller;
    private JTabbedPane tabbedPane;
    private RegistroPanel registro;
    private BuscaPanel busca;

    public ArmazemFrame(){
        initComponent();
        this.setVisible(true);
    }

    public void initComponent(){

        controller = new ArmazemController();

        this.setTitle("Armazens");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(700,500);
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
        new ArmazemFrame();
    }
}
