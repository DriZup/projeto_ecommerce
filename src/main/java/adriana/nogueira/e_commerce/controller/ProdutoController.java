package adriana.nogueira.e_commerce.controller;

import adriana.nogueira.e_commerce.model.Produto;
import adriana.nogueira.e_commerce.service.ProdutoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletarProduto(@PathVariable Long id) {
        Map<String, String> response = produtoService.deletarProduto(id);
        return ResponseEntity.ok(response);
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
