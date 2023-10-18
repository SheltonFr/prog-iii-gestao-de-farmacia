package view.main;

import model.Administrador;
import view.administrador.CadastroAdmin;
import view.administrador.Login;
import view.armazem.ArmazemFrame;
import view.cliente.RegistroCliente;
import view.filtro.Filtro;
import view.fornecedor.RegistroFornecedor;
import view.produto.RegistroProduto;

import javax.swing.*;
import java.awt.*;


public class Principal extends JFrame {

    private JMenuBar menuBar;
    private JMenu conta;
    private JMenuItem cadastrar, alterar, perfil;
    private JLabel admName;
    Administrador administrador;

    private OptionsPane optionsPane;
    private DetailsPane detailsPane;
    public Principal (Administrador administrador) {

        this.administrador = administrador;
        this.setTitle("Principal");
        initComponents();
        this.setVisible(true);
    }

    private void initComponents(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(650, 450));
        this.setLayout(new GridLayout(1, 2));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        menuBar = new JMenuBar();

        optionsPane = new OptionsPane();
        detailsPane = new DetailsPane(administrador);
        this.add(optionsPane);
        this.add(detailsPane);

        // menu de conta
        cadastrar = new JMenuItem("Cadastrar Administrador");
        alterar = new JMenuItem("Alterar Conta");
        perfil = new JMenuItem("Perfil");


        // Eventos

        cadastrar.addActionListener(e ->{
            new CadastroAdmin();
        });

        alterar.addActionListener(e ->{
            this.administrador = null;
            this.dispose();
            new Login();
        });

        conta = new JMenu("Conta"){{
            add(cadastrar);
            add(alterar);
            add(perfil);
        }};

        menuBar.add(conta);

        this.setJMenuBar(menuBar);
    }


    public static void main(String[] args) {
        new Principal(new Administrador());
    }
}