package adriana.nogueira.e_commerce.service;

import adriana.nogueira.e_commerce.dto.CompraDTO;
import adriana.nogueira.e_commerce.dto.ProdutoDTO;
import adriana.nogueira.e_commerce.exceptions.ClienteNaoEncontradoException;
import adriana.nogueira.e_commerce.exceptions.ProdutoIndisponivelException;
import adriana.nogueira.e_commerce.exceptions.ProdutoNaoEncontradoException;
import adriana.nogueira.e_commerce.model.Cliente;
import adriana.nogueira.e_commerce.model.Compra;
import adriana.nogueira.e_commerce.model.Produto;
import adriana.nogueira.e_commerce.repository.ClienteRepository;
import adriana.nogueira.e_commerce.repository.CompraRepository;
import adriana.nogueira.e_commerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // Validar CPF e buscar cliente
        Cliente cliente = clienteRepository.findByCpf(compraDTO.getCpf())
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com o CPF: " + compraDTO.getCpf()));

        // Validar lista de produtos
        List<ProdutoDTO> produtosDTO = compraDTO.getProdutos();
        if (produtosDTO == null || produtosDTO.isEmpty()) {
            throw new IllegalArgumentException("A lista de produtos não pode ser nula ou vazia.");
        }

        // Buscar produtos pelo nome e validar
        List<Produto> produtos = produtosDTO.stream()
                .map(this::buscarProdutoPorNome)
                .collect(Collectors.toList());

        // Criar e salvar a compra
        Compra compra = new Compra();
        compra.setCliente(cliente);
        compra.setProdutos(produtos);

        return compraRepository.save(compra);
    }

    private Produto buscarProdutoPorNome(ProdutoDTO produtoDTO) {
        return produtoRepository.findByNome(produtoDTO.getNome())
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado com o nome: " + produtoDTO.getNome()));
    }
}