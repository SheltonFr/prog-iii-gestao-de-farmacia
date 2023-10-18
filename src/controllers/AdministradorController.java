package controllers;

import FileManagement.FileManager;
import farmaciaExceptios.AdmNotFoundException;
import farmaciaExceptios.AdminAlreadyExistsException;
import model.Administrador;

import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AdministradorController {

    private String fileName = "adm.txt";
    private File file = FileManager.createFile(fileName);
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;


    private boolean saveAtFile(ArrayList<Administrador> lista) throws IOException{
        objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(lista);
        objectOutputStream.flush();
        objectOutputStream.close();
        return true;
    }

    public ArrayList<Administrador> getLista(){
        ArrayList<Administrador> lista = new ArrayList<>();

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            lista = (ArrayList<Administrador>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.getMessage();
        }

        return lista;
    }


    public boolean cadastrar(String nome, String user, String pass) throws AdminAlreadyExistsException {
        boolean cadastrado = false;
        ArrayList<Administrador> lista = getLista();
        boolean exists = false;
        Administrador administrador = new Administrador(nome, user, pass, LocalDateTime.now());

        for (Administrador adm:
             lista) {
            if ((adm.getUsername()).equals(administrador.getUsername())) {
                exists = true;
                break;
            }
        }

        if(!exists)
            lista.add(administrador);
         else
            throw new AdminAlreadyExistsException(administrador);


        try {
            if(saveAtFile(lista)) JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return  cadastrado;
    }

    public Administrador login(String username, String password) throws AdmNotFoundException {
        ArrayList<Administrador> lista = getLista();
        for (Administrador adm : lista) {
            if(adm.getUsername().equals(username) && adm.getPassword().equals(password))
                return adm;
        }

        throw new AdmNotFoundException("Credenciais invalidas!");
    }

    public void limparLista() {
        ArrayList<Administrador> lista = getLista();
        lista.clear();
        try {
            saveAtFile(lista);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



    public static void main(String[] args) {

        new AdministradorController().limparLista();
        //System.out.println(new AdministradorController().getLista());

    }
}
