package adriana.nogueira.e_commerce.service;

import adriana.nogueira.e_commerce.model.Produto;

import java.util.List;
import java.util.Map;

public interface ProdutoService {


    public List<Produto> listarProdutos();
    Produto salvar(Produto produto);
}
