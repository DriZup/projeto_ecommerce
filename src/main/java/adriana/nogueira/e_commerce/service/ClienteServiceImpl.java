package adriana.nogueira.e_commerce.service;

import adriana.nogueira.e_commerce.model.Cliente;
import adriana.nogueira.e_commerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        validarCpfEEmail(cliente);
        return clienteRepository.save(cliente);
    }

    @Override
    public void atualizarCliente(Cliente cliente) {
        validarCliente(cliente);
        if (!clienteRepository.existsById(cliente.getId())) {
            throw new IllegalArgumentException("Não existe um cliente cadastrado com esse ID.");
        }
        validarCpfEEmail(cliente);
        clienteRepository.save(cliente);
    }

    @Override
    public void excluirCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Não existe um cliente cadastrado com esse ID.");
        }
        clienteRepository.deleteById(id);
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não existe um cliente cadastrado com esse ID."));
    }

    @Override
    public Optional<Cliente> buscarPorCpf(String cpf) {
        Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);
        if (cliente == null) {
            throw new IllegalArgumentException("Não existe um cliente cadastrado com esse CPF.");
        }
        return cliente;
    }

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    private void validarCpfEEmail(Cliente cliente) {
        Optional<Cliente> clienteExistentePorCpf = clienteRepository.findByCpf(cliente.getCpf());
        if (clienteExistentePorCpf.isPresent() && !clienteExistentePorCpf.get().getId().equals(cliente.getId())) {
            throw new IllegalArgumentException("Já existe outro cliente cadastrado com esse CPF.");
        }

        Cliente clienteExistentePorEmail = clienteRepository.findByEmail(cliente.getEmail());
        if (clienteExistentePorEmail != null && !clienteExistentePorEmail.getId().equals(cliente.getId())) {
            throw new IllegalArgumentException("Já existe outro cliente cadastrado com esse e-mail.");
        }
    }

    private void validarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente não pode ser vazio ou nulo.");
        }
        if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF do cliente não pode ser vazio ou nulo.");
        }
        if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail do cliente não pode ser vazio ou nulo.");
        }
    }
}