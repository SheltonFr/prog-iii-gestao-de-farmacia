package controllers;

import FileManagement.FileManager;
import farmaciaExceptios.FornecedorAlreadyExistsException;
import farmaciaExceptios.InvalidValueException;
import model.Fornecedor;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FornecedorController {
    private String filename = "fornecedores.txt";
    private File file = FileManager.createFile(filename);
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public boolean saveAtFile(ArrayList<Fornecedor> fornecedores) throws IOException {
        objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(fornecedores);
        objectOutputStream.flush();
        objectOutputStream.close();
        return true;
    }

    public ArrayList<Fornecedor> getLista() {
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(file));
            fornecedores = (ArrayList<Fornecedor>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.getMessage();
        }

        return fornecedores;
    }

    public Fornecedor getById(int id){
        Fornecedor Fornecedor = null;
        ArrayList<Fornecedor> lista = getLista();

        for (Fornecedor forn : lista) {
            if(forn.getNumero() == id){
                Fornecedor = forn;
                break;
            }
        }

        return Fornecedor;
    }

    public Fornecedor getByName(String name){
        Fornecedor fornecedor = null;
        ArrayList<Fornecedor> lista = getLista();

        for (Fornecedor forn : lista) {
            if(forn.getNome().contains(name) || forn.getNome().equalsIgnoreCase(name)){
                fornecedor = forn;
                break;
            }
        }

        return fornecedor;
    }

    public Fornecedor getByIdAndByName(int id, String name){
        Fornecedor fornecedor = null;
        ArrayList<Fornecedor> lista = getLista();

        for (Fornecedor forn : lista) {
            if(forn.getNumero() == id && (forn.getNome().equalsIgnoreCase(name)||forn.getNome().contains(name))){
                fornecedor = forn;
                break;
            }
        }

        return fornecedor;
    }

    public Fornecedor getByNuit(String nuit) {
        Fornecedor fornecedor = null;
        ArrayList<Fornecedor> lista = getLista();
        for (Fornecedor fornecedor1 : lista) {
            if(fornecedor1.getNuit().equalsIgnoreCase(nuit)){
                fornecedor = fornecedor1;
                break;
            }
        }
        return  fornecedor;
    }

    public Fornecedor validateInputsAndSearchFornecedor(String id, String name, String nuit) throws InvalidValueException {
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
                return getById(numero);
            } catch (NumberFormatException e){
                throw new InvalidValueException("Insira apenas numeros no campo Numero!");
            }
        }

        if(!nuit.isBlank()){
            return getByNuit(nuit);
        }

        return null;
    }

    public void cadastrar(Integer numero, String nome, String nuit) throws FornecedorAlreadyExistsException {
        ArrayList<Fornecedor> fornecedores = getLista();
        boolean exists = false;
        Fornecedor Fornecedor= new Fornecedor(numero, nome, nuit, LocalDateTime.now());

        for (Fornecedor forn:
                fornecedores) {
            if (forn.getNome().equalsIgnoreCase(Fornecedor.getNome())) {
                exists = true;
                break;
            }
        }

        if(!exists)
            fornecedores.add(Fornecedor);
        else
            throw new FornecedorAlreadyExistsException(Fornecedor);


        try {
            saveAtFile(fornecedores);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public  boolean actualizar(int id, String nome, String nuit) throws InvalidValueException {
        ArrayList<Fornecedor> fornecedores = getLista();
        if(!nome.isBlank() && !nuit.isBlank()){
            fornecedores.get(id).setNome(nome);
            fornecedores.get(id).setNuit(nuit);

            try {
                saveAtFile(fornecedores);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }

        throw new InvalidValueException("Preencha os campos devidamente");

    }

    public void deleteById(int id) throws IOException {
        ArrayList<Fornecedor> fornecedores = getLista();
        fornecedores.remove(id);
        saveAtFile(fornecedores);
    }

    public ArrayList<String> getNames(){
        ArrayList<Fornecedor> fornecedores = getLista();
        ArrayList<String> nomes = new ArrayList<>();

        for (Fornecedor fornecedor:
             fornecedores) {
            nomes.add(fornecedor.getNome());
        }

        return nomes;
    }

    public void limparLista() {
        ArrayList<Fornecedor> fornecedors = getLista();
        fornecedors.clear();
        try {
            saveAtFile(fornecedors);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public int getNextPosition() {
        ArrayList<Fornecedor> Fornecedors = (ArrayList<Fornecedor>) getLista();
        return Fornecedors.size() + 1;
    }

    public Fornecedor getByIndex(int index){
        return getLista().get(index);
    }

    public int getSize(){
        return getLista().size();
    }



}
