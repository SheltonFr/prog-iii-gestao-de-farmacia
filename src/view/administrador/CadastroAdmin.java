package view.administrador;

import controllers.AdministradorController;
import farmaciaExceptios.AdminAlreadyExistsException;
import view.utils.Layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class CadastroAdmin extends JFrame implements ActionListener {

    private JTextField nameField, userField;
    private JPasswordField passField;
    private JLabel none, user, pass, title;
    private JButton cadastrar, limpar,cancelar;
    private LinkedList<JLabel> lbls;

    public CadastroAdmin() {
        this.initComponent();
        this.addToFrame();



        this.setVisible(true);
    }

    private void initComponent() {
        this.setTitle("Cadastro administrador");
        this.setLayout(new GridBagLayout());
        this.setSize(600, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        none = new JLabel("Nome completo: ");
        user = new JLabel("Usuario: ");
        pass = new JLabel("Senha: ");
        lbls = new LinkedList<>(){
            {
                add(none);
                add(user);
                add(pass);
            }
        };
        title = new JLabel("Cadastro de Administrador");
        title.setFont(new Font("Dialog", Font.CENTER_BASELINE, 16));

        lbls.forEach(lbl -> {
            lbl.setFont(new Font(Font.DIALOG, Font.CENTER_BASELINE, 14));
        });

        passField = new JPasswordField(15);
        nameField = new JTextField(15);
        userField = new JTextField(15);

        cadastrar = new JButton("Cadastrar");
        cancelar = new JButton("Cancelar");
        limpar = new JButton("Limpar");

        cadastrar.addActionListener(this);
        limpar.addActionListener(this);
        cancelar.addActionListener(this);


    }


    private void addToFrame() {

        Layout.constraints.insets.bottom = 60;
        Layout.constraints.anchor = GridBagConstraints.CENTER;
        Layout.addComponent(this, title, 0,0,3,1);

        Layout.constraints.insets = new Insets(10, 10, 6,6);
        Layout.constraints.anchor = GridBagConstraints.EAST;
        Layout.addComponent(this, none, 1,0,1,1);
        Layout.addComponent(this, nameField, 1,1,2,1);
        Layout.addComponent(this, user, 2,0,1,1);
        Layout.addComponent(this, userField, 2,1,2,1);
        Layout.addComponent(this, pass, 3,0,1,1);
        Layout.addComponent(this, passField, 3,1,2,1);

        Layout.constraints.insets.top = 40;
        Layout.addComponent(this, cancelar, 4,0,1,1);
        Layout.addComponent(this, limpar, 4,1,1,1);
        Layout.addComponent(this, cadastrar, 4,2,1,1);

    }


    private void limparCampos(){
        nameField.setText("");
        userField.setText("");
        passField.setText("");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cadastrar){
            AdministradorController controller = new AdministradorController();

            var nome = nameField.getText();
            var user = userField.getText();
            var pass = passField.getText();

            boolean cadastro = false;
            if(!nome.isBlank() && !user.isBlank() && !pass.isBlank()){
                try {
                    cadastro = controller.cadastrar(nome, user, pass);
                    new Login();
                } catch (AdminAlreadyExistsException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            }
        }

        if(e.getSource() == limpar){
            limparCampos();
        }

        if(e.getSource() == cancelar) this.dispose();
    }



    public static void main(String[] args) {

        new CadastroAdmin();
    }
}
