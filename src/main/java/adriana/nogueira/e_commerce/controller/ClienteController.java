package adriana.nogueira.e_commerce.controller;

import adriana.nogueira.e_commerce.model.Cliente;
import adriana.nogueira.e_commerce.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/salvar")
    public ResponseEntity<?> cadastrarCliente(@Valid @RequestBody Cliente cliente) {
        try {
            Cliente clienteSalvo = clienteService.salvarCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }
}
