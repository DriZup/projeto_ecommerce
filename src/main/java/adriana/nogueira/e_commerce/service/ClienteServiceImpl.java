package adriana.nogueira.e_commerce.service;

import adriana.nogueira.e_commerce.model.Cliente;
import adriana.nogueira.e_commerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public Cliente salvarCliente(Cliente cliente) {
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com esse CPF.");
        }

        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com esse email.");
        }
        clienteRepository.save(cliente);

        return cliente;
    }

    @Override
    public void atualizarCliente(Cliente cliente) {
        if (!clienteRepository.existsById(cliente.getId())) {
            throw new IllegalArgumentException("Não existe um cliente cadastrado com esse ID.");
        }
        Cliente clienteExistentePorCpf = clienteRepository.findByCpf(cliente.getCpf());
        if (clienteExistentePorCpf != null && !clienteExistentePorCpf.getId().equals(cliente.getId())) {
            throw new IllegalArgumentException("Já existe outro cliente cadastrado com esse CPF.");
        }

        Cliente clienteExistentePorEmail = clienteRepository.findByEmail(cliente.getEmail());
        if (clienteExistentePorEmail != null && !clienteExistentePorEmail.getId().equals(cliente.getId())) {
            throw new IllegalArgumentException("Já existe outro cliente cadastrado com esse email.");
        }

        clienteRepository.save(cliente);
    }


    @Override
    public void excluirCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Não existe um cliente cadastrado com esse ID.");
        }

        clienteRepository.deleteById(id);
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não existe um cliente cadastrado com esse ID."));
    }

    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }
}
