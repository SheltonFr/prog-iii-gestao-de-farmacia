package controllers;

import FileManagement.FileManager;
import farmaciaExceptios.ClienteAlreadyExistsException;
import farmaciaExceptios.InvalidValueException;
import model.Cliente;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ClienteController {

    private String filename = "clientes.txt";
    private File file = FileManager.createFile(filename);
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public boolean saveAtFile(ArrayList<Cliente> clientes) throws IOException {
        objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(clientes);
        objectOutputStream.flush();
        objectOutputStream.close();
        return true;
    }

    public ArrayList<Cliente> getLista() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            clientes = (ArrayList<Cliente>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.getMessage();
        }

        return clientes;
    }

    public void cadastrar(Integer id, String nome, String bi) throws ClienteAlreadyExistsException {
        ArrayList<Cliente> clientes = getLista();
        boolean exists = false;
        Cliente Cliente= new Cliente(id, nome, bi, LocalDate.now());

        for (Cliente cli:
                clientes) {
            if (cli.getNome().equalsIgnoreCase(Cliente.getNome())) {
                exists = true;
                break;
            }
        }

        if(!exists)
            clientes.add(Cliente);
        else
            throw new ClienteAlreadyExistsException(Cliente);


        try {
            saveAtFile(clientes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Cliente getByIdAndByName(int id, String name){
        Cliente cliente = null;
        ArrayList<Cliente> lista = getLista();

        for (Cliente cli : lista) {
            if(cli.getId() == id && (cli.getNome().equalsIgnoreCase(name)||cli.getNome().contains(name))){
                cliente = cli;
                break;
            }
        }

        return cliente;
    }

    public Cliente getByName(String name){
        Cliente cliente = null;
        ArrayList<Cliente> lista = getLista();

        for (Cliente cli : lista) {
            if(cli.getNome().contains(name) || cli.getNome().equalsIgnoreCase(name)){
                cliente = cli;
                break;
            }
        }

        return cliente;
    }

    public Cliente getByBi(String bi) {
        Cliente cliente = null;
        ArrayList<Cliente> lista = getLista();
        for (Cliente cli : lista) {
            if(cli.getBi().equalsIgnoreCase(bi)){
                cliente = cli;
                break;
            }
        }
        return  cliente;
    }
    public Cliente validateInputsAndSearchCliente(String id, String name, String bi) throws InvalidValueException {
        if(!id.isBlank() && !name.isBlank()){
            try{
                int numero = Integer.parseInt(id);
                return getByIdAndByName(numero, name);
            } catch (NumberFormatException e){
                throw new InvalidValueException("Insira apenas numeros no campo Numero!");
            }
        }

        if(id.isBlank() && !name.isBlank()){
            return getByName(name);
        }

        if(!id.isBlank() && name.isBlank()){
            try{
                int numero = Integer.parseInt(id);
                return getByIndex(numero);
            } catch (NumberFormatException e){
                throw new InvalidValueException("Insira apenas numeros no campo Numero!");
            }
        }

        if(!bi.isBlank()){
            return getByBi(bi);
        }

        return null;
    }

    public Cliente getById(int id){
        ArrayList<Cliente> lista = getLista();
        for (Cliente cliente : lista) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }

        return null;
    }
    
    public Cliente getByIndex(int index){
        return getLista().get(index);
    }


    public int getSize(){
        return getLista().size();
    }

    public void limparLista() {
        ArrayList<Cliente> Clientes = getLista();
        Clientes.clear();
        try {
            saveAtFile(Clientes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteById(int id) throws IOException {
        ArrayList<Cliente> clientes = getLista();
        clientes.remove(id);
        saveAtFile(clientes);
    }


    public int getNextPosition() {
        return getSize() + 1;
    }

    public  boolean actualizar(int id, String nome, String bi) throws InvalidValueException {
        ArrayList<Cliente> clientes = getLista();
        if(!nome.isBlank() && !bi.isBlank()){
            clientes.get(id).setNome(nome);
            clientes.get(id).setBi(bi);

            try {
                saveAtFile(clientes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }

        throw new InvalidValueException("Preencha os campos devidamente");

    }

    public ArrayList<String> getNomes() {
        ArrayList<String> nomes = new ArrayList<>();
        ArrayList<Cliente> lista = getLista();

        for (Cliente cliente : lista) {
            nomes.add(cliente.getNome());
        }

        return nomes;
    }

    public static void main(String[] args) {
        ClienteController clienteController = new ClienteController();

        ArrayList<Cliente> lista = clienteController.getLista();

        System.out.println(clienteController.getById(1).toString());

    }
}
