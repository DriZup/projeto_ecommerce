package adriana.nogueira.e_commerce.service;

import adriana.nogueira.e_commerce.model.Produto;
import adriana.nogueira.e_commerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto salvarProduto(Produto produto) {
        if (produtoRepository.existsByNome(produto.getNome())) {
            throw new IllegalArgumentException("Produto com o mesmo nome já cadastrado!");
        }
        return produtoRepository.save(produto);
    }

    @Override
    public Produto atualizarProduto(Produto produto) {
        if (!produtoRepository.existsById(produto.getId())) {
            throw new IllegalArgumentException("Produto com o ID " + produto.getId() + " nao encontrado!");
        }
        return produtoRepository.save(produto);
    }

    @Override
    public void deletarProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new IllegalArgumentException("Produto com o ID " + id + " nao encontrado!");
        }
        produtoRepository.deleteById(id);
    }

    @Override
    public Iterable<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @Override
    public Iterable<Produto> buscarProdutosPorNome(String nome) {
        Iterable<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(nome);
        if (!produtos.iterator().hasNext()) {
            throw new IllegalArgumentException("Nenhum produto com o nome '" + nome + "' foi encontrado!");
        }
        return produtos;
    }

}
