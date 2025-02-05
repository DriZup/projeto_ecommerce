package adriana.nogueira.e_commerce.controller;


import adriana.nogueira.e_commerce.model.Cliente;
import adriana.nogueira.e_commerce.repository.ClienteRepository;
import adriana.nogueira.e_commerce.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {



    private  ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> salvarCliente(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteService.salvarCliente(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> buscarPorCpf(@PathVariable String cpf) {
        Cliente cliente = clienteService.findByCpf(cpf);
        return ResponseEntity.ok(cliente);
    }
}
