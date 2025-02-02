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
        validarProduto(produto);
        verificarProdutoDuplicado(produto);
        return produtoRepository.save(produto);
    }

    @Override
    public Produto atualizarProduto(Produto produto) {
        if (!produtoRepository.existsById(produto.getId())) {
            throw new IllegalArgumentException("Produto com o ID " + produto.getId() + " não encontrado!");
        }
        validarProduto(produto);
        return produtoRepository.save(produto);
    }

    @Override
    public void deletarProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new IllegalArgumentException("Produto com o ID " + id + " não encontrado!");
        }
        produtoRepository.deleteById(id);
    }

    @Override
    public Iterable<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @Override
    public Iterable<Produto> buscarProdutosPorNome(String nome) {
        if (produtoRepository.existsByNome(nome)) {
            return produtoRepository.findByNomeContainingIgnoreCase(nome);
        } else {
            throw new IllegalArgumentException("Produto com o nome " + nome + " não encontrado!");
        }
    }

    private void validarProduto(Produto produto) {
        if (produto == null || produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Produto não pode ser nulo ou com nome vazio!");
        }
        if (produto.getPreco() == null || produto.getPreco() < 0) {
            throw new IllegalArgumentException("O preço do produto não pode ser menor que zero!");
        }
        if (produto.getQuantidade() == null || produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("A quantidade do produto não pode ser menor que zero!");
        }
    }

    private void verificarProdutoDuplicado(Produto produto) {
        if (produtoRepository.existsByNome(produto.getNome())) {
            throw new IllegalArgumentException("Produto com o mesmo nome já cadastrado!");
        }
    }
}