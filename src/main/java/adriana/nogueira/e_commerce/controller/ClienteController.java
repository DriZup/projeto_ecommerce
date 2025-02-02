package adriana.nogueira.e_commerce.controller;

import adriana.nogueira.e_commerce.model.Cliente;
import adriana.nogueira.e_commerce.service.ClienteServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @PostMapping("/salvar")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
       Cliente salvarCliente = clienteService.salvarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvarCliente);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Cliente> atualizarCliente(@Valid @RequestBody Cliente cliente) {
        clienteService.atualizarCliente(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        Cliente buscarPorId = clienteService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(buscarPorId);
    }

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos() {
        Iterable<Cliente> buscarTodos = clienteService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(buscarTodos);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/buscar/cpf/{cpf}")
    public ResponseEntity<Optional<Cliente>> buscarPorCpf(@PathVariable String cpf) {
        Optional<Cliente> cliente = clienteService.buscarPorCpf(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }
}
