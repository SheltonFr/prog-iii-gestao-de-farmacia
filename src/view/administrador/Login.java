package view.administrador;

import controllers.AdministradorController;
import farmaciaExceptios.AdmNotFoundException;
import model.Administrador;
import view.main.Principal;
import view.utils.Layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    private JLabel lblUsuario;
    private JLabel lblPass;
    private JTextField txtUsuario;
    private JPasswordField txtPass;
    private JButton submeter;
    private JButton sair;
    Container container;

    AdministradorController controller = new AdministradorController();

    public Login () {
        super("Login Administrador");
        this.setLayout(new GridBagLayout());
        this.setSize(650, 400);
        container = this.getContentPane();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        initComponent();
        addToFrame();
        this.setVisible(true);
    }


    public void initComponent() {
        // inicializar componentes
        lblUsuario = new JLabel("Usuario");
        lblPass = new JLabel("Password");
        txtUsuario = new JTextField(15);
        txtPass = new JPasswordField(15);
        submeter = new JButton("Submeter");
        sair = new JButton("Sair");

        // eventos
        sair.addActionListener(this);
        submeter.addActionListener(this);
    }

    public void addToFrame() {

        Layout.constraints.anchor = GridBagConstraints.EAST;
        Layout.constraints.insets = new Insets(10,10,10,10);
        Layout.addComponent(container, lblUsuario, 0,0,1,1);
        Layout.addComponent(container, txtUsuario, 0,1,2,1);
        Layout.addComponent(container, lblPass, 1,0,1,1);
        Layout.addComponent(container, txtPass, 1,1,2,1);
        Layout.addComponent(container, sair, 2,0,1,1);
        Layout.addComponent(container, submeter, 2,1,1,1);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submeter){
            var user = txtUsuario.getText();
            var pass = txtPass.getText();

            Administrador adm;

            try {
                adm = controller.login(user, pass);
                this.dispose();
                new Principal(adm);
            } catch (AdmNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getSource() == sair) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
