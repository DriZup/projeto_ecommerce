package adriana.nogueira.e_commerce.controller;

import adriana.nogueira.e_commerce.model.Produto;
import adriana.nogueira.e_commerce.service.ProdutoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoServiceImpl produtoService;

    @PostMapping("/salvar")
    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) {
        Produto salvarProduto = produtoService.salvarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvarProduto);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Produto> atualizarProduto(@RequestBody Produto produto) {
        Produto atualizarProduto = produtoService.atualizarProduto(produto);
        return ResponseEntity.status(HttpStatus.OK).body(atualizarProduto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Produto>> listarProdutos() {
        Iterable<Produto> listarProdutos = produtoService.listarProdutos();
        return ResponseEntity.status(HttpStatus.OK).body(listarProdutos);
    }

    @GetMapping("/buscar/{nome}")
    public ResponseEntity<Iterable<Produto>> buscarProdutosPorNome(@PathVariable String nome) {
        Iterable<Produto> buscarProdutosPorNome = produtoService.buscarProdutosPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(buscarProdutosPorNome);
    }
}
