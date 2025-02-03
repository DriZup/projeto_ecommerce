package adriana.nogueira.e_commerce.service;

import adriana.nogueira.e_commerce.model.Produto;

import java.util.Map;

public interface ProdutoService {

    public Produto salvarProduto(Produto produto);

    public Produto atualizarProduto(Produto produto);

    public Map<String, String> deletarProduto(Long id);

    public Iterable<Produto> listarProdutos();

    public Iterable<Produto> buscarProdutosPorNome(String nome);

}
