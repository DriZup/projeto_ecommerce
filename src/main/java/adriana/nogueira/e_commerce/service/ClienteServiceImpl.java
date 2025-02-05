package adriana.nogueira.e_commerce.service;

import adriana.nogueira.e_commerce.model.Cliente;
import adriana.nogueira.e_commerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente salvarCliente(Cliente cliente) {
        if (cliente.getCpf() == null || cliente.getCpf().length() != 11 || !cliente.getCpf().matches("\\d+")) {
            throw new IllegalArgumentException("O CPF deve conter exatamente 11 dígitos numéricos.");
        }

        // Verifica se o CPF já existe
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new IllegalArgumentException("Erro ao salvar cliente: CPF já cadastrado.");
        }

        // Verifica se o EMAIL já existe
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("Erro ao salvar cliente: E-mail já cadastrado.");
        }

        try {
            // Salva o cliente no banco de dados
            return clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Erro ao salvar cliente: Dados duplicados.");
        } catch (Exception e) {
            // Trata outros erros inesperados
            throw new RuntimeException("Erro inesperado ao salvar cliente: " + e.getMessage());
        }
    }


    @Override
    public Cliente findByCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente com CPF " + cpf + " não encontrado.");
        }
        return cliente;
    }
}
