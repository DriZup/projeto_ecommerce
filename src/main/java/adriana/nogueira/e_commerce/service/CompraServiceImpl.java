package adriana.nogueira.e_commerce.service;

import adriana.nogueira.e_commerce.dto.CompraDTO;
import adriana.nogueira.e_commerce.dto.ProdutoDTO;
import adriana.nogueira.e_commerce.exceptions.ClienteNaoEncontradoException;
import adriana.nogueira.e_commerce.exceptions.ProdutoEmFaltaException;
import adriana.nogueira.e_commerce.exceptions.ProdutoIndisponivelException;
import adriana.nogueira.e_commerce.exceptions.ProdutoNaoEncontradoException;
import adriana.nogueira.e_commerce.model.Cliente;
import adriana.nogueira.e_commerce.model.Compra;
import adriana.nogueira.e_commerce.model.Produto;
import adriana.nogueira.e_commerce.repository.ClienteRepository;
import adriana.nogueira.e_commerce.repository.CompraRepository;
import adriana.nogueira.e_commerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CompraRepository compraRepository;
    private Produto produto;


    @Override
    public Compra realizarCompra(CompraDTO compraDTO) {
        try {
            Cliente cliente = clienteRepository.findByCpf(compraDTO.getCpf());
            List<ProdutoDTO> produtosDTO = compraDTO.getProdutos();
            if (produtosDTO == null || produtosDTO.isEmpty()) {
                throw new IllegalArgumentException("A lista de produtos não pode ser nula ou vazia.");
            }
            List<Produto> produtos = produtosDTO.stream()
                    .map(this::buscarProdutoPorNome)
                    .collect(Collectors.toList());

            List<String> produtosEmFalta = produtos.stream()
                    .filter(produto -> produto.getQuantidade() == 0)
                    .map(Produto::getNome)
                    .collect(Collectors.toList());

            if (!produtosEmFalta.isEmpty()) {
                throw new ProdutoEmFaltaException(produtosEmFalta);
            }

            Compra compra = new Compra();
            compra.setCliente(cliente);
            compra.setProdutos(produtos);
            return compraRepository.save(compra);

        } catch (ClienteNaoEncontradoException e) {
            throw new ClienteNaoEncontradoException(e.getMessage());
        } catch (ProdutoEmFaltaException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto em falta: " + e.getProdutosEmFalta());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao realizar a compra: " + e.getMessage());
        }
    }

    private Produto buscarProdutoPorNome(ProdutoDTO produtoDTO) {
        return produtoRepository.findByNome(produtoDTO.getNome())
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado com o nome: " + produtoDTO.getNome()));
    }
}