package adriana.nogueira.e_commerce.controller;

import adriana.nogueira.e_commerce.dto.ProdutoDTO;
import adriana.nogueira.e_commerce.model.Produto;
import adriana.nogueira.e_commerce.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDTO> salvarProduto(@RequestBody ProdutoDTO produtoDTO) {
        Produto produto = produtoDTO.toModel();
        Produto produtoSalvo = produtoService.salvar(produto);
        ProdutoDTO response = ProdutoDTO.fromModel(produtoSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }
}
