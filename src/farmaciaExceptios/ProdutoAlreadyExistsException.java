package farmaciaExceptios;

import model.Produto;

public class ProdutoAlreadyExistsException extends Exception {

    public ProdutoAlreadyExistsException(Produto produto){
        super("Produto  " + produto.getNome() + " ja existe!");
    }
}
