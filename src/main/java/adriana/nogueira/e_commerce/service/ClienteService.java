package adriana.nogueira.e_commerce.service;

import adriana.nogueira.e_commerce.model.Cliente;
import adriana.nogueira.e_commerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvarCliente(Cliente cliente) {
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new IllegalArgumentException("CPF ja cadastrado!");

        }
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("E-mail ja cadastrado!");
        }
        return clienteRepository.save(cliente);
    }
}
