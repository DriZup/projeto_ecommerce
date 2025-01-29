package adriana.nogueira.e_commerce.service;

import adriana.nogueira.e_commerce.model.Produto;
import adriana.nogueira.e_commerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto) {
        if(produtoRepository.existsByNome(produto.getNome())) {
            throw new IllegalArgumentException("Produto com o mesmo nome já cadastrado!");
        }
        return produtoRepository.save(produto);
    }
}
