package adriana.nogueira.e_commerce.controller;



import adriana.nogueira.e_commerce.dto.CompraDTO;
import adriana.nogueira.e_commerce.exceptions.ProdutoIndisponivelException;
import adriana.nogueira.e_commerce.model.Compra;
import adriana.nogueira.e_commerce.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity<Compra> realizarCompra(@RequestBody CompraDTO compraDTO) throws ProdutoIndisponivelException {
        Compra compra = compraService.realizarCompra(compraDTO);
        return ResponseEntity.ok(compra);
    }
}