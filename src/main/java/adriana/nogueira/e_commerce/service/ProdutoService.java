package adriana.nogueira.e_commerce.service;

import adriana.nogueira.e_commerce.model.Produto;

public interface ProdutoService {

    public Produto salvarProduto(Produto produto);

    public Produto atualizarProduto(Produto produto);

    public void deletarProduto(Long id);

    public Iterable<Produto> listarProdutos();

    public Iterable<Produto> buscarProdutosPorNome(String nome);

}
