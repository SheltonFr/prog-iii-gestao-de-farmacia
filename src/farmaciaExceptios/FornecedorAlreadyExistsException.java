package farmaciaExceptios;

import model.Armazem;
import model.Fornecedor;

public class FornecedorAlreadyExistsException extends Exception{

    public FornecedorAlreadyExistsException(Fornecedor fornecedor){
        super("Ja existe um fornecedor cadastrado como: " + fornecedor.getNome() + "!");
    }
}
