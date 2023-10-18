package farmaciaExceptios;

import model.Armazem;
import model.Cliente;

public class ClienteAlreadyExistsException extends Exception{

    public ClienteAlreadyExistsException(Cliente cliente){
        super("Ja existe um armazem cadastrado como: " + cliente.getNome() + "!");
    }
}
